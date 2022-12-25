package initial;

import initial.code.code_integration;
import initial.code.dart_code_update;
import initial.code.short_code_update;
import initial.code.update_status;
import initial.price.price_update;
import load_save.CSV;

import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;
import initial.price.index_update;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class initial_update {
    private static final String save_route_for_updage_log = "D:\\Drive\\Code\\bacado\\csv\\list\\kospi_integration.csv";
    // 매핑 코드
    private static final String save_route_for_kospi_integration = "D:\\Drive\\Code\\bacado\\csv\\list\\kospi_integration.csv";
    private static final String save_route_for_kosdak_integration = "D:\\Drive\\Code\\bacado\\csv\\list\\kosdak_integration.csv";
    // 새로운 코드 집합
    private static final String save_route_for_kospi_new = "D:\\Drive\\Code\\bacado\\csv\\list\\kospi_new.csv";
    private static final String save_route_for_kosdak_new = "D:\\Drive\\Code\\bacado\\csv\\list\\kosdak_new.csv";
    // 매핑 불가능한 코드 => 우선주
    private static final String save_route_for_kospi_left = "D:\\Drive\\Code\\bacado\\csv\\list\\kospi_left.csv";
    private static final String save_route_for_kosdak_left = "D:\\Drive\\Code\\bacado\\csv\\list\\kosdak_left.csv";
    // 코드 업데이트 현황
    private static final String save_route_for_kospi_status = "D:\\Drive\\Code\\bacado\\csv\\list\\kospi_status.csv";
    private static final String save_route_for_kodak_status = "D:\\Drive\\Code\\bacado\\csv\\list\\kosdak_status.csv";
    // 업데이트 필요한 코드
    private static final String save_route_for_kospi_update_list = "D:\\Drive\\Code\\bacado\\csv\\list\\kospi_update_list.csv";
    private static final String save_route_for_kodak_update_list = "D:\\Drive\\Code\\bacado\\csv\\list\\kosdak_update_list.csv";
    public static void Code_update() throws IOException, ParserConfigurationException, SAXException, ParseException, InterruptedException {
        // 화 ~ 토 하루씩 한번 작동
        // 코드 업데이트
        // short_code update
        short_code_update.Short_code_update();
        // new_short_code가 old_short_code와 동일하면 작동 안함
        if(short_code_update.same_short_code){
            // dart_code update
            dart_code_update.Dart_code_update();
            // kospi_code integration
            code_integration.Code_integration(dart_code_update.dart_list, short_code_update.short_list.get(0), "kospi",
                    save_route_for_kospi_integration, save_route_for_kospi_new, save_route_for_kospi_left);
            // kosdak_code integration
            code_integration.Code_integration(dart_code_update.dart_list, short_code_update.short_list.get(1), "kosdak",
                    save_route_for_kosdak_integration, save_route_for_kosdak_new, save_route_for_kosdak_left);

        }
        // update_list가 존재하면 작동하도록 수정 필요
        if(false){
            ifrs_update.download_financial(CSV.readCSV(save_route_for_kospi_update_list), "kospi");
            ifrs_update.download_financial(CSV.readCSV(save_route_for_kodak_update_list), "kosdak");
        }
        // 재무제표 다운로드가 발생하면 작동
        if(true){
            // code_status_update, code_update_list_update
            update_status.Update_status(CSV.readCSV(save_route_for_kospi_integration ), "kospi",
                    save_route_for_kospi_status, save_route_for_kospi_update_list);
            update_status.Update_status(CSV.readCSV(save_route_for_kosdak_integration), "kosdak",
                    save_route_for_kodak_status, save_route_for_kodak_update_list);
        }
        // 가격 업데이트
        if(true){
            //price update
            price_update.Price_update(CSV.readCSV(save_route_for_kospi_integration), CSV.readCSV(save_route_for_kosdak_integration));
            //index update
            index_update.Index_update();
        }
    }
}
