package initial.index;

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
import java.util.List;

import static initial.setting.krx_api_key;
import static initial.setting.standard_date;

public class commodity_series_update {
    private static final String krx_series_api = "http://data-dbg.krx.co.kr/svc/apis/idx/krx_dd_trd.json?basDd=";
    private static final String kospi_series_api = "http://data-dbg.krx.co.kr/svc/apis/idx/kospi_dd_trd.json?basDd=";
    private static final String kosdak_series_api = "http://data-dbg.krx.co.kr/svc/apis/idx/kosdaq_dd_trd.json?basDd=";
    private static final String save_route_for_krx_series = "D:\\Drive\\Code\\bacado\\csv\\index\\krx_series\\";
    private static final String save_route_for_kospi_series = "D:\\Drive\\Code\\bacado\\csv\\index\\kospi_series\\";
    private static final String save_route_for_kosdak_series = "D:\\Drive\\Code\\bacado\\csv\\index\\kosdak_series\\";
    public static void Index_update() throws IOException, ParseException {
        api_read(krx_series_api, save_route_for_krx_series);
        api_read(kospi_series_api, save_route_for_kospi_series);
        api_read(kosdak_series_api, save_route_for_kosdak_series);
    }
    private static void api_read(String api_route, String save_route) throws IOException, ParseException {
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
            String tmp = ((JSONObject) o).get("IDX_NM").toString().replace(" ", "").replace("/","_");
            CSV.writeCSVoverride(save_route + "\\" + tmp + ".csv",
                    List.of(List.of(
                            ((JSONObject) o).get("BAS_DD").toString(), // 기준일자 20200414,
                            tmp, // 지수명 KRX 300
                            ((JSONObject) o).get("CLSPRC_IDX").toString(), // 종가 11150
                            ((JSONObject) o).get("CMPPREVDD_IDX").toString(), // 대비
                            ((JSONObject) o).get("FLUC_RT").toString(), // 등락률
                            ((JSONObject) o).get("OPNPRC_IDX").toString(), // 시가 11200
                            ((JSONObject) o).get("HGPRC_IDX").toString(), // 고가 11100
                            ((JSONObject) o).get("LWPRC_IDX").toString(), // 저가 610438
                            ((JSONObject) o).get("ACC_TRDVOL").toString(), // 거래량 6816285500
                            ((JSONObject) o).get("ACC_TRDVAL").toString(), // 거래대금 3909296563200
                            ((JSONObject) o).get("MKTCAP").toString() // 상장시가총액 349044336
                    )));
        }
    }
}
