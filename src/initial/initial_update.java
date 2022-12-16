package initial;

import initial.code.code_integration;
import initial.code.dart_code_update;
import initial.code.short_code_update;
import initial.code.update_status;
import initial.index.index_update;
import load_save.CSV;
import load_save.ListToHashMap;

import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class initial_update {
    private static final String save_route_for_updage_log = "D:\\Drive\\Code\\bacado\\csv\\list\\kospi_integration.csv";
    private static final String save_route_for_kospi_integration = "D:\\Drive\\Code\\bacado\\csv\\list\\kospi_integration.csv";
    private static final String save_route_for_kosdak_integration = "D:\\Drive\\Code\\bacado\\csv\\list\\kosdak_integration.csv";
    private static final String save_route_for_kospi_left = "D:\\Drive\\Code\\bacado\\csv\\list\\kospi_left.csv";
    private static final String save_route_for_kosdak_left = "D:\\Drive\\Code\\bacado\\csv\\list\\kosdak_left.csv";
    private static final String save_route_for_kospi_status = "D:\\Drive\\Code\\bacado\\csv\\list\\kospi_status.csv";
    private static final String save_route_for_kodak_status = "D:\\Drive\\Code\\bacado\\csv\\list\\kosdak_status.csv";
    private static final String save_route_for_kospi_update_list = "D:\\Drive\\Code\\bacado\\csv\\list\\kospi_update_list.csv";
    private static final String save_route_for_kodak_update_list = "D:\\Drive\\Code\\bacado\\csv\\list\\kosdak_update_list.csv";
    public static void Code_update() throws IOException, ParserConfigurationException, SAXException, ParseException {
        // check_update 업데이트 중복 방지
        if(check_update.Check_update(save_route_for_updage_log)){
            //price update
            price_update.Price_update(CSV.readCSV(save_route_for_kospi_integration), CSV.readCSV(save_route_for_kosdak_integration));
            //index update
            index_update.Index_update();
            // dart_code update
            dart_code_update.Dart_code_update();
            // short_code update
            short_code_update.Short_code_update();
            // dart_code list to hashmap
            HashMap<String, String> dart_code = ListToHashMap.listTohashMap(dart_code_update.dart_list, 1, 0); // corp_name, corp_cord
            // kospi_code integration
            code_integration.Code_integration(short_code_update.short_list.get(0), dart_code,
                    save_route_for_kospi_integration, save_route_for_kospi_left);
            // kosdak_code integration
            code_integration.Code_integration(short_code_update.short_list.get(1), dart_code,
                    save_route_for_kosdak_integration, save_route_for_kosdak_left);
        }
        // code_status_update, code_update_list_update
        update_status.Update_status(CSV.readCSV(save_route_for_kospi_integration ), "kospi",
                save_route_for_kospi_status, save_route_for_kospi_update_list);
        update_status.Update_status(CSV.readCSV(save_route_for_kosdak_integration), "kosdak",
                save_route_for_kodak_status, save_route_for_kodak_update_list);
    }
}
