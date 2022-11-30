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


//

public class price_financial {
    private String dart_code;
    private String short_code;
    private final String year = "2021";
    private final String half = "11011";
    private final String fs = "CFS";
    private String response1 = "";
    private String response2 = "";

    public void price_financial(String dart_code, String short_code) throws IOException, ParseException {
        if(short_code == null){
            dart_code = "244455"; // 삼성전자
            short_code = "33780"; // 삼성전자
        }
        int length = dart_code.length();
        if(length < 8){
            for(var i = 0; i < (8 - length); i++){
                dart_code = "0" + dart_code;
            }
        }
        //URL
        String url_plus1 = "https://opendart.fss.or.kr/api/fnlttSinglAcntAll.json?crtfc_key=8b84c49305eb0a97b4729cdc268004cb2b41ae36&corp_code=" + dart_code + "&bsns_year=" + year + "&reprt_code=" + half + "&fs_div=" + fs;
        String url_plus2 = "http://apis.data.go.kr/1160100/service/GetStockSecuritiesInfoService/getStockPriceInfo?serviceKey=esy%2BILqhjceVIiQy96oHrDjiEKhM9TVTNUIovRo954WK32I1pCD8QkU6PVbIRrxa5R9mUYJo9y7fiKde77op5w%3D%3D&resultType=json&likeSrtnCd="+short_code;
        URL url1 = new URL(url_plus1);
        URL url2 = new URL(url_plus2);
        //
        BufferedReader bf1;
        BufferedReader bf2;
        bf1 = new BufferedReader(new InputStreamReader(url1.openStream(), StandardCharsets.UTF_8));
        bf2 = new BufferedReader(new InputStreamReader(url2.openStream(), StandardCharsets.UTF_8));
        response1 = bf1.readLine();
        response2 = bf2.readLine();
        //
        JSONParser jsonParser = new JSONParser();
        JSONObject myjson1 = (JSONObject)jsonParser.parse(response1);
        JSONObject myjson2 = (JSONObject)jsonParser.parse(response2);
        //
        JSONArray value1 = (JSONArray)myjson1.get("list");
        //
        JSONObject response = (JSONObject)myjson2.get("response");
        JSONObject body = (JSONObject)response.get("body");
        JSONObject items = (JSONObject)body.get("items");
        JSONArray item = (JSONArray)items.get("item");
        JSONObject value2 = (JSONObject)item.get(0);
        // Financial JASON
        var value1_status = myjson1.get("status");
        //
        var arr = new ArrayList<>();
        //
        arr.add(value2.get("basDt")); // 시간 basDt
        arr.add(value2.get("clpr")); // 종가 clpr
        arr.add(value2.get("trqu")); // 거래량 trqu
        arr.add(value2.get("mrktTotAmt")); // 시가총액 mrktTotAmt
        arr.add(value2.get("lstgStCnt")); // 상장좌수 lstgStCnt
        //
        if(value1_status == "000"){
            for(var i = 0; i < value1.size(); i++){
                arr.add(((JSONObject)value1.get(6)).get("account_id") + " " + ((JSONObject)value1.get(7)).get("account_nm") + " " + ((JSONObject)value1.get(8)).get("account_detail"));
                //arr.add(((JSONObject)value1.get(6)).get("account_id")); // account_id 6
                //arr.push(((JSONObject)value1.get(7)).get("account_nm")); // account_nm 7
                //arr.push(((JSONObject)value1.get(8)).get("account_detail")); // account_detail 8
                //arr.push(" ");
            }
        }
        else{
            arr.add("금융회사");
        }
        for(int i = 0; i < arr.size(); i++) {
            System.out.println(arr.get(i));
        }
    }
    //
    //

}
