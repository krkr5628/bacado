package initial.code;

import load_save.CSV;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class code_integration {
    public static void Code_integration(List<List<String>> short_code, HashMap<String, String> dart_code, String save_route_for_integration, String save_route_for_left){
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
    }
}
