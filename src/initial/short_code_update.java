package initial;

import load_save.CSV;
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
    private static final String date = "20221206"; // 변경하고 개발 / 전날 입력
    private static final String key = ""; // 입력하고 개발
    private static final String kospi_api = "https://data-dbg.krx.co.kr/svc/apis/sto/stk_isu_base_info.json?basDd=";
    private static final String kosdak_api = "http://data-dbg.krx.co.kr/svc/apis/sto/ksq_isu_base_info.json?basDd=";
    private static final String kospi_save_route = "D:\\Drive\\Code\\bacado\\csv\\list\\kospi_code.csv";
    private static final String kosdak_save_route = "D:\\Drive\\Code\\bacado\\csv\\list\\kosdak_code.csv";
    public static void Short_code_update() throws IOException, ParseException {
        api_read(kospi_api,kospi_save_route);
        api_read(kosdak_api,kosdak_save_route);
    }
    private static void api_read(String tmp, String tmp2) throws IOException, ParseException {
        List<List<String>> arr = new ArrayList<>();
        String url_plus = tmp + date + "&AUTH_KEY=" + key;
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
            arr.add(List.of(((JSONObject) o).get("ISU_SRT_CD").toString(), // 단축코드 095570
                    ((JSONObject) o).get("ISU_NM").toString(), // 한글 종목명 AJ네트웍스보통주
                    ((JSONObject) o).get("ISU_ABBRV").toString(), // 한글 종목약명 AJ네트웍스
                    ((JSONObject) o).get("LIST_SHRS").toString() // 상장주식수 46822295
            ));
        }
        CSV.writeCSV(tmp2, arr);
    }

}
