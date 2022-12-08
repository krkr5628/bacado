package initial;

import load_save.CSV;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class code_update {
    private static final String dart_route = "D:\\Drive\\Code\\bacado\\csv\\list\\dart_code.csv";
    private static final String kospi_route = "D:\\Drive\\Code\\bacado\\csv\\list\\kospi_code.csv";
    private static final String kosdak_route = "D:\\Drive\\Code\\bacado\\csv\\list\\kosdak_code.csv";
    private static final String kospi_save = "D:\\Drive\\Code\\bacado\\csv\\list\\kospi_integration.csv";
    private static final String kosdak_save = "D:\\Drive\\Code\\bacado\\csv\\list\\kosdak_integration.csv";

    public static void Code_update(){
        HashMap<String, String> dart_code = ListToHashMap(CSV.readCSV(dart_route));
        List<List<String>> kospi_code = CSV.readCSV(kospi_route);
        List<List<String>> kosdak_code = CSV.readCSV(kosdak_route);
        //
        integration(kospi_code, dart_code, kospi_save);
        integration(kosdak_code, dart_code, kosdak_save);
    }
    private static HashMap<String, String> ListToHashMap(List<List<String>> input_list){
        HashMap<String, String> tmp = new HashMap<>();
        for(List<String> line : input_list){
            tmp.put(line.get(0), line.get(1));
        }
        return tmp;
    }
    private static void integration(List<List<String>> first, HashMap<String, String> second, String save_route){
        List<List<String>> save_file = new ArrayList<>();
        for(List<String> tmp : first){
            if(second.containsKey(tmp.get(0))){
                save_file.add(List.of(tmp.get(0),second.get(tmp.get(0)),tmp.get(1),tmp.get(2)));
            }
        }
        CSV.writeCSV(save_route, save_file);
    }

}
