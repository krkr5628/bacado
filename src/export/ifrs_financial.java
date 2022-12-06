package export;

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


public class ifrs_financial {
    private int cnt = 0;
    private static final String key = ""; // 입력하고 개발
    public List<List<String>> financial_save;
    public void download_financial(String dart_code, String year, String half, String fs) throws IOException, ParseException {
        int length = dart_code.length();
        if(length < 8){
            for(var i = 0; i < (8 - length); i++){
                dart_code = "0" + dart_code;
            }
        }
        //
        String url_plus1 = "https://opendart.fss.or.kr/api/fnlttSinglAcntAll.json?crtfc_key=" + key + "&corp_code=" + dart_code + "&bsns_year=" + year + "&reprt_code=" + half + "&fs_div=" + fs;
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
            for(var i = 0; i < value1.size(); i++){
                arr.add(List.of(((JSONObject)value1.get(i)).get("sj_nm").toString(), // 재무상태표
                        ((JSONObject)value1.get(i)).get("account_id").toString(), // ifrs-full_CurrentAssets
                        ((JSONObject)value1.get(i)).get("account_nm").toString(), // 유동자산
                        ((JSONObject)value1.get(i)).get("account_detail").toString(), // -
                        ((JSONObject)value1.get(i)).get("thstrm_nm").toString(), // 53기
                        ((JSONObject)value1.get(i)).get("thstrm_amount").toString() // 218163185000000
                        ));
            }
        }
        else if(value1_status.equals("013")){
            arr.add(List.of("금융회사"));
        }
        System.out.println(cnt++);
        financial_save = arr;
    }
}
/*
        public static HashSet<String> jong_bok = new HashSet<>();
        jong_bok.add(((JSONObject)value1.get(i)).get("sj_nm") + "," + ((JSONObject)value1.get(i)).get("account_id") + "," + ((JSONObject)value1.get(i)).get("account_nm") + "," + ((JSONObject)value1.get(i)).get("account_detail"));
        //
        String url_plus2 = "http://apis.data.go.kr/1160100/service/GetStockSecuritiesInfoService/getStockPriceInfo?serviceKey=esy%2BILqhjceVIiQy96oHrDjiEKhM9TVTNUIovRo954WK32I1pCD8QkU6PVbIRrxa5R9mUYJo9y7fiKde77op5w%3D%3D&resultType=json&likeSrtnCd="+short_code;
        URL url2 = new URL(url_plus2);
        BufferedReader bf2;
        bf2 = new BufferedReader(new InputStreamReader(url2.openStream(), StandardCharsets.UTF_8));
        //
        String response2 = bf2.readLine();
        //
        JSONObject myjson2 = (JSONObject)jsonParser.parse(response2);
        //
        JSONObject response = (JSONObject)myjson2.get("response");
        JSONObject body = (JSONObject)response.get("body");
        JSONObject items = (JSONObject)body.get("items");
        JSONArray item = (JSONArray)items.get("item");
        JSONObject value2 = (JSONObject)item.get(0);
        //
        arr.add(value2.get("basDt")); // 시간 basDt
        arr.add(value2.get("clpr")); // 종가 clpr
        arr.add(value2.get("trqu")); // 거래량 trqu
        arr.add(value2.get("mrktTotAmt")); // 시가총액 mrktTotAmt
        arr.add(value2.get("lstgStCnt")); // 상장좌수 lstgStCnt
        */