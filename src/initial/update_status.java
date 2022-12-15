package initial;

import load_save.CSV;
import static initial.setting.*;
import static initial.update_list.Update_list;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class update_status {
    private static final String[] half = {"11013", "11012", "11014","11011"};
    private static final String route_for_dart_code = "D:\\Drive\\Code\\bacado\\csv\\";
    public static void Update_status(List<List<String>> code_list, String market, String save, String save2){
        List<List<String>> status_tmp = update(code_list, market, Integer.toString(end_year), end_half);
        CSV.writeCSV(save, status_tmp);
        Update_list(status_tmp, save2);
    }
    private static List<List<String>> update(List<List<String>> code, String market, String year, int half_code){
        List<List<String>> save = new ArrayList<>();
        for (int i = 0; i < code.size(); i++){
            List<String> line = code.get(i);
            save.add(check(i, line, market, year, half_code));
        }
        return save;
    }
    private static List<String> check(int idx, List<String> line, String market, String year, int half_code){
        if(Files.exists(Path.of(route_for_dart_code + market + "\\" + line.get(1) + "\\"
                + line.get(1) + "_price_history.csv"))) {
            if(Files.exists(Path.of(route_for_dart_code + market + "\\" + line.get(1) + "\\"
                    + line.get(1) + "_" + year + "_" + half[half_code] + "_" + "OFS" + ".csv"))){
                return List.of(line.get(1), year + "-" + half[half_code], "Y", "non_financial_company");
            }
            else{
                return List.of(line.get(1), "2011-11011", "Y", "update");
            }
        }
        else{
            if(Files.exists(Path.of(route_for_dart_code + market + "\\" + line.get(1) + "\\"
                    + line.get(1) + "_" + year + "_" + half[half_code] + "_" + "OFS" + ".csv"))){
                return List.of(line.get(1), year + "-" + half[half_code], "N", "non_financial_company");
            }
            else{
                return List.of(line.get(1), "2011-11011", "N", "update");
            }
        }
    }
}
