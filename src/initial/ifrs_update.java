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

import static initial.setting.*;

public class ifrs_update {
    private static final String write_route_for_dart_code = "D:\\Drive\\Code\\bacado\\csv\\";
    private static boolean check_financial_company = true;
    private static int cnt = 0;
    public static void download_financial(List<List<String>> code, String market) throws IOException, ParseException {
        for(int i = 0; i < 215; i++){
            List<String> line = code.get(i);
            check_financial_company = true;
            for(int j = 2012; j <= end_year; j++) {
                String year_w = Integer.toString(j);
                for (int k = 0; k < 4; k++) {
                    connect_api(line.get(0), year_w, half[k], "CFS", market);
                    connect_api(line.get(0), year_w, half[k], "OFS", market);
                    if(j == end_year && k == end_half){
                        if(check_financial_company){
                            CSV.writeCSV(write_route_for_dart_code + market + "\\" + line.get(0) + "\\"
                                    + line.get(0) + "_financial_history.csv", List.of(List.of("financial_history")));
                        }
                        break;
                    }
                }
            }
        }
    }
    private static void connect_api(String dart_code, String year, String half, String fs, String market) throws IOException, ParseException {
        String url_plus1 = "https://opendart.fss.or.kr/api/fnlttSinglAcntAll.json?crtfc_key="
                + dart_api_key + "&corp_code=" + dart_code + "&bsns_year=" + year + "&reprt_code=" + half + "&fs_div=" + fs;
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
            check_financial_company = false;
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