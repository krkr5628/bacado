package initial.code;

import load_save.CSV;
import static initial.setting.krx_api_key;
import static initial.setting.standard_date;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class short_code_update {
    private static final String kospi_api = "https://data-dbg.krx.co.kr/svc/apis/sto/stk_isu_base_info.json?basDd=";
    private static final String kosdak_api = "http://data-dbg.krx.co.kr/svc/apis/sto/ksq_isu_base_info.json?basDd=";
    private static final String save_route_for_kospi_code = "D:\\Drive\\Code\\bacado\\csv\\list\\kospi_code.csv";
    private static final String save_route_for_kosdak_code = "D:\\Drive\\Code\\bacado\\csv\\list\\kosdak_code.csv";
    public static List<List<List<String>>> short_list = new ArrayList<>();
    public static void Short_code_update() throws IOException, ParseException {
        api_read(kospi_api, save_route_for_kospi_code);
        api_read(kosdak_api, save_route_for_kosdak_code);
    }
    private static void api_read(String api_route, String save_route) throws IOException, ParseException {
        List<List<String>> tmp = new ArrayList<>();
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
        for (Object o : value) {
            tmp.add(List.of(((JSONObject) o).get("ISU_SRT_CD").toString(), // 단축코드 095570
                    ((JSONObject) o).get("ISU_ABBRV").toString() // 한글 종목명 AJ네트웍스
            ));
        }
        check_overlap(tmp, save_route);
    }
    private static void check_overlap(List<List<String>> update_code, String save_route){
        List<List<String>> old_code_list = CSV.readCSV(save_route); // 기존 리스트
        List<List<String>> new_code_list = new ArrayList<>(); // 새로운 리스트
        //
        HashMap<String, String> old_code_list_Map_krx_name = ListToHashMap.listTohashMap(old_code_list,0,1); //krx_code-한글명
        HashMap<String, String> old_code_list_Map_dart_name = ListToHashMap.listTohashMap(old_code_list,0,2); //krx_code-호환 한글명
        //
        for(List<String> tmp : update_code){
            // krx 코드 다름
            if(!old_code_list_Map_krx_name.containsKey(tmp.get(0))){
                new_code_list.add(List.of(tmp.get(0), tmp.get(1), tmp.get(1)));
            }
            // krx 코드 동일 - krx 한글명 다름
            else if(old_code_list_Map_krx_name.containsKey(tmp.get(0)) && !old_code_list_Map_krx_name.get(tmp.get(0)).equals(tmp.get(1))){
                new_code_list.add(List.of(tmp.get(0), tmp.get(1), "***" + tmp.get(1)));
            }
            // krx 코드 및 krx 한글명 동일
            else{
                new_code_list.add(List.of(tmp.get(0), tmp.get(1), old_code_list_Map_dart_name.get(tmp.get(0))));
            }
        }
        //
        short_list.add(new_code_list);
        CSV.writeCSV(save_route, new_code_list);
    }
}
