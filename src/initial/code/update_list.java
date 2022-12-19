package initial.code;

import load_save.CSV;

import java.util.ArrayList;
import java.util.List;


public class update_list {
    public static void Update_list(String route, List<List<String>> update_list){
        List<List<String>> save_file = new ArrayList<>();
        for(List<String> tmp : update_list){
            if(tmp.get(3).equals("update")){
                save_file.add(tmp);
            }
        }
        CSV.writeCSV(route, save_file);
    }
}
