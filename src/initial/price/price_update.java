package initial.price;

import static initial.setting.krx_api_key;
import static initial.setting.standard_date;

import load_save.CSV;
import load_save.ListToHashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

public class price_update {
    private static final String kospi_api = "http://data-dbg.krx.co.kr/svc/apis/sto/stk_bydd_trd.json?basDd=";
    private static final String kosdak_api = "http://data-dbg.krx.co.kr/svc/apis/sto/ksq_bydd_trd.json?basDd=";
    private static final String save_route_for_kospi_price = "D:\\Drive\\Code\\bacado\\csv\\kospi\\";
    private static final String save_route_for_kosdak_price = "D:\\Drive\\Code\\bacado\\csv\\kosdak\\";
    public static void Price_update(List<List<String>> kospi_integration, List<List<String>> kosdak_integration) throws IOException, ParseException, InterruptedException {
        HashMap<String, String> kospi_integraion_hashmap = ListToHashMap.listTohashMap(kospi_integration, 0, 1);
        HashMap<String, String> kosdak_integraion_hashmap = ListToHashMap.listTohashMap(kosdak_integration,0,1);
        //
        System.out.println(standard_date + " price_update_start");
        api_read(kospi_api, save_route_for_kospi_price, kospi_integraion_hashmap);
        api_read(kosdak_api, save_route_for_kosdak_price, kosdak_integraion_hashmap);
        System.out.println(standard_date + " price_update_finish");
    }
    private static void api_read(String api_route, String save_route, HashMap<String, String> integration_hashmap) throws IOException, ParseException {
        String dart_code;
        String url_plus = api_route + standard_date + "&AUTH_KEY=" + krx_api_key;
        URL url = new URL(url_plus);
        //
        BufferedReader bf;
        //
        bf = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
        //
        String response = bf.readLine();
        //
        JSONParser jsonParser = new JSONParser();
        JSONObject myjson = (JSONObject)jsonParser.parse(response);
        JSONArray value = (JSONArray)myjson.get("OutBlock_1");
        //
        if(value.isEmpty()) return;
        //
        for (Object o : value) {
            dart_code = integration_hashmap.get(((JSONObject) o).get("ISU_CD"));
            if(dart_code == null) continue;
            //
            CSV.writeCSVoverride(save_route + "\\" + dart_code + "\\" + dart_code + "_price_history.csv",
                    List.of(List.of(((JSONObject) o).get("BAS_DD").toString(), // ???????????? 20200414
                            dart_code, // ???????????? 088980??? ???????????? dart_code 00435297 ??????
                            ((JSONObject) o).get("TDD_CLSPRC").toString(), // ?????? 11200
                            ((JSONObject) o).get("TDD_OPNPRC").toString(), // ?????? 11150
                            ((JSONObject) o).get("TDD_HGPRC").toString(), // ?????? 11200
                            ((JSONObject) o).get("TDD_LWPRC").toString(), // ?????? 11100
                            ((JSONObject) o).get("ACC_TRDVOL").toString(), // ????????? 610438
                            ((JSONObject) o).get("ACC_TRDVAL").toString(), // ???????????? 6816285500
                            ((JSONObject) o).get("MKTCAP").toString(), // ???????????? 3909296563200
                            ((JSONObject) o).get("LIST_SHRS").toString() // ??????????????? 349044336
                    )));
        }
    }
}
