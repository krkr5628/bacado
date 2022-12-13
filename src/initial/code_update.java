package initial;

import load_save.CSV;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class code_update {
    private static final String save_route_for_kospi_integration = "D:\\Drive\\Code\\bacado\\csv\\list\\kospi_integration.csv";
    private static final String save_route_for_kosdak_integration = "D:\\Drive\\Code\\bacado\\csv\\list\\kosdak_integration.csv";
    private static final String save_route_for_kospi_left = "D:\\Drive\\Code\\bacado\\csv\\list\\kospi_left.csv";
    private static final String save_route_for_kosdak_left = "D:\\Drive\\Code\\bacado\\csv\\list\\kosdak_left.csv";
    private static final String save_route_for_kospi_status = "D:\\Drive\\Code\\bacado\\csv\\list\\kospi_status.csv";
    private static final String save_route_for_kodak_status = "D:\\Drive\\Code\\bacado\\csv\\list\\kosdak_status.csv";
    private static final String save_route_for_kospi_update_list = "D:\\Drive\\Code\\bacado\\csv\\list\\kospi_update_list.csv";
    private static final String save_route_for_kodak_update_list = "D:\\Drive\\Code\\bacado\\csv\\list\\kosdak_update_list.csv";

    public static void Code_update() throws IOException, ParserConfigurationException, SAXException, ParseException {
        dart_code_update.Dart_code_update();
        short_code_update.Short_code_update();
        //
        HashMap<String, String> dart_code = ListToHashMap(dart_code_update.dart_list);
        //
        update_status.Update_status(integration(short_code_update.short_list.get(0), dart_code,
                        save_route_for_kospi_integration, save_route_for_kospi_left),
                "kospi", save_route_for_kospi_status, save_route_for_kospi_update_list);
        update_status.Update_status(integration(short_code_update.short_list.get(1), dart_code,
                        save_route_for_kosdak_integration, save_route_for_kosdak_left),
                "kosdak", save_route_for_kodak_status, save_route_for_kodak_update_list);
    }
    private static HashMap<String, String> ListToHashMap(List<List<String>> input_list){
        HashMap<String, String> tmp = new HashMap<>();
        for(List<String> line : input_list){
            tmp.put(line.get(1), line.get(0)); // corp_name, corp_cord
        }
        return tmp;
    }
    private static List<List<String>> integration(List<List<String>> short_code, HashMap<String, String> dart_code, String save_route_for_integration, String save_route_for_left){
        List<List<String>> integration_save_file = new ArrayList<>();
        List<List<String>> integration_left_file = new ArrayList<>();
        for(List<String> tmp : short_code){
            if(dart_code.containsKey(tmp.get(2))){
                integration_save_file.add(List.of(tmp.get(0), dart_code.get(tmp.get(2)), tmp.get(2)));
            }
            else{
                integration_left_file.add(List.of(tmp.get(0),tmp.get(1)));
            }
        }
        CSV.writeCSV(save_route_for_integration, integration_save_file);
        CSV.writeCSV(save_route_for_left, integration_left_file);
        return integration_save_file;
    }

}
