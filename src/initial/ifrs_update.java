package initial;

import static initial.setting.dart_api_key;

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

public class ifrs_update {
    private static int cnt = 0;
    private static final String write_route_for_dart_code = "D:\\Drive\\Code\\bacado\\csv\\";
    public static void download_financial(String dart_code, String year, String half, String fs, String market) throws IOException, ParseException {
        String url_plus1 = "https://opendart.fss.or.kr/api/fnlttSinglAcntAll.json?crtfc_key=" + dart_api_key + "&corp_code=" + dart_code + "&bsns_year=" + year + "&reprt_code=" + half + "&fs_div=" + fs;
        URL url1 = new URL(url_plus1);
        //
        BufferedReader bf1;
        //
        bf1 = new BufferedReader(new InputStreamReader(url1.openStream(), StandardCharsets.UTF_8));
        //
        String response1 = bf1.readLine();
        //
        JSONParser jsonParser = new JSONParser();
        JSONObject myjson1 = (JSONObject)jsonParser.parse(response1);
        JSONArray value1 = (JSONArray)myjson1.get("list");
        String value1_status = myjson1.get("status").toString();
        //
        List<List<String>> arr = new ArrayList<>();
        if(value1_status.equals("000")){
            for (Object o : value1) {
                arr.add(List.of(((JSONObject) o).get("sj_nm").toString(), // 재무상태표
                        ((JSONObject) o).get("account_id").toString(), // ifrs-full_CurrentAssets
                        ((JSONObject) o).get("account_nm").toString(), // 유동자산
                        ((JSONObject) o).get("account_detail").toString(), // -
                        ((JSONObject) o).get("thstrm_nm").toString(), // 53기
                        ((JSONObject) o).get("thstrm_amount").toString() // 218163185000000
                ));
            }
            CSV.writeCSV(write_route_for_dart_code + market + "\\" + dart_code + "\\"
                    + dart_code + "_" + year + "_" + half + "_" + fs + ".csv", arr);
        }
        System.out.println(cnt++);
    }
}