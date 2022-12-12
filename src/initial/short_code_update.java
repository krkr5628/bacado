package initial;

import load_save.CSV;
import static initial.setting.krx_api_key;
import static initial.setting.short_code_date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class short_code_update {
    private static final String kospi_api = "https://data-dbg.krx.co.kr/svc/apis/sto/stk_isu_base_info.json?basDd=";
    private static final String kosdak_api = "http://data-dbg.krx.co.kr/svc/apis/sto/ksq_isu_base_info.json?basDd=";
    private static final String kospi_save_route = "D:\\Drive\\Code\\bacado\\csv\\list\\kospi_code.csv";
    private static final String kosdak_save_route = "D:\\Drive\\Code\\bacado\\csv\\list\\kosdak_code.csv";
    public static List<List<List<String>>> short_list = new ArrayList<>();
    public static void Short_code_update() throws IOException, ParseException {
        api_read(kospi_api,kospi_save_route);
        api_read(kosdak_api,kosdak_save_route);
    }
    private static void api_read(String api_route, String save_route) throws IOException, ParseException {
        List<List<String>> tmp = new ArrayList<>();
        String url_plus = api_route + short_code_date + "&AUTH_KEY=" + krx_api_key;
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
        for (Object o : value) {
            tmp.add(List.of(((JSONObject) o).get("ISU_ABBRV").toString(), // 단축코드 095570
                    ((JSONObject) o).get("ISU_SRT_CD").toString(), // 한글 종목약명 AJ네트웍스 ISU_SRT_CD
                    ((JSONObject) o).get("LIST_SHRS").toString() // 상장주식수 46822295
            ));
        }
        short_list.add(tmp);
        CSV.writeCSV(save_route, tmp);
    }

}
