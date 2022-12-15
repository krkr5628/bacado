package initial;

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
import java.util.HashMap;
import java.util.List;

public class price_update {
    private static final String kospi_api = "http://data-dbg.krx.co.kr/svc/apis/sto/stk_bydd_trd.json?basDd=";
    private static final String kosdak_api = "http://data-dbg.krx.co.kr/svc/apis/sto/ksq_bydd_tr.json?basDd=";
    private static final String save_route_for_kospi_price = "D:\\Drive\\Code\\bacado\\csv\\kospi\\";
    private static final String save_route_for_kosdak_price = "D:\\Drive\\Code\\bacado\\csv\\kosdak\\";
    public static void Price_update(List<List<String>> kospi_integration, List<List<String>> kosdak_integration) throws IOException, ParseException {
        HashMap<String, String> kospi_integraion_hashmap = ListToHashMap.listTohashMap(kospi_integration, 0, 1);
        HashMap<String, String> kosdak_integraion_hashmap = ListToHashMap.listTohashMap(kosdak_integration,0,1);
        //
        api_read(kospi_api, save_route_for_kospi_price, kospi_integraion_hashmap);
        api_read(kosdak_api, save_route_for_kosdak_price, kosdak_integraion_hashmap);
    }
    private static void api_read(String api_route, String save_route, HashMap<String, String> integration_hashmap) throws IOException, ParseException {
        String dart_code;
        String url_plus = api_route + standard_date + "&AUTH_KEY=" + krx_api_key;
        URL url = new URL(url_plus);
        //
        BufferedReader bf;
        //
        try{
            bf = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
        } catch(IOException e){
            return;
        }
        //
        String response = bf.readLine();
        //
        JSONParser jsonParser = new JSONParser();
        JSONObject myjson = (JSONObject)jsonParser.parse(response);
        JSONArray value = (JSONArray)myjson.get("OutBlock_1");
        //
        for (Object o : value) {
            try{
                dart_code = integration_hashmap.get(((JSONObject) o).get("ISU_CD"));
            } catch (NullPointerException e){
                continue;
            }
            //
            CSV.writeCSVoverride(save_route + "\\" + dart_code + "\\" + dart_code + "_price_history.csv",
                    List.of(List.of(((JSONObject) o).get("BAS_DD").toString(), // 기준일자 20200414
                            dart_code, // 종목코드 088980에 해당하는 dart_code 00435297 매칭
                            ((JSONObject) o).get("TDD_CLSPRC").toString(), // 종가 11200
                            ((JSONObject) o).get("TDD_OPNPRC").toString(), // 시가 11150
                            ((JSONObject) o).get("TDD_HGPRC").toString(), // 고가 11200
                            ((JSONObject) o).get("TDD_LWPRC").toString(), // 저가 11100
                            ((JSONObject) o).get("ACC_TRDVOL").toString(), // 거래량 610438
                            ((JSONObject) o).get("ACC_TRDVAL").toString(), // 거래대금 6816285500
                            ((JSONObject) o).get("MKTCAP").toString(), // 시가총액 3909296563200
                            ((JSONObject) o).get("LIST_SHRS").toString() // 상장주식수 349044336
                    )));
        }
    }
}
