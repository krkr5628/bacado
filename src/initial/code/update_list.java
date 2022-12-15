package initial.code;

import load_save.CSV;

import java.util.ArrayList;
import java.util.List;

import static initial.setting.current_status;

public class update_list {
    public static void Update_list(List<List<String>> update_list, String route){
        List<List<String>> save_file = new ArrayList<>();
        for(List<String> tmp : update_list){
            if(!tmp.get(1).equals(current_status) && !tmp.get(2).equals("financial_company")){
                save_file.add(tmp);
            }
        }
        CSV.writeCSV(route, save_file);
    }
}
