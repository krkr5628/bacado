package initial.code;

import load_save.CSV;
import static initial.setting.*;
import static initial.code.update_list.Update_list;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class update_status {
    private static final String[] half = {"11013", "11012", "11014","11011"};
    private static final String route_for_dart_code = "D:\\Drive\\Code\\bacado\\csv\\";
    public static void Update_status(List<List<String>> code_list, String market, String save, String save2){
        List<List<String>> status_tmp = update(code_list, market);
        CSV.writeCSV(save, status_tmp);
        Update_list(save2, status_tmp);
    }
    private static List<List<String>> update(List<List<String>> code, String market){
        List<List<String>> save = new ArrayList<>();
        for (List<String> line : code) {
            save.add(check(line, market));
        }
        return save;
    }
    private static List<String> check(List<String> line, String market){
        if(Files.exists(Path.of(route_for_dart_code + market + "\\" + line.get(1) + "\\"
                + line.get(1) + "_financial_company.csv"))) {
            return List.of(line.get(1), line.get(2), "2011-11011", "financial_company");
        }
        String update_line = "2015-11011";
        for(int j = 2012; j <= 2022; j++) {
            for (int k = 0; k < 4; k++) {
                if(Files.exists(Path.of(route_for_dart_code + market + "\\" + line.get(1) + "\\"
                        + line.get(1) + "_" + j + "_" + half[k] + "_" + "OFS" + ".csv"))
                        || Files.exists(Path.of(route_for_dart_code + market + "\\" + line.get(1) + "\\"
                        + line.get(1) + "_" + j + "_" + half[k] + "_" + "CFS" + ".csv"))){
                    update_line = j + "-" + half[k];
                }
            }
        }
        if(update_line.equals("2015-11011")){
            return List.of(line.get(1), line.get(2), update_line, "update");
        }
        if(!update_line.equals("2015-11011") && !update_line.equals(standard_status)){
            return List.of(line.get(1), line.get(2), update_line, "fs_differ");
        }
        return List.of(line.get(1), line.get(2), update_line, "non_financial_company");

    }
}
