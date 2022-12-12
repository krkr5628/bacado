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
        List<List<String>> status_tmp = update(code_list, market);
        CSV.writeCSV(save, status_tmp);
        Update_list(status_tmp, save2);
    }
    private static List<List<String>> update(List<List<String>> code, String market){
        List<List<String>> save = new ArrayList<>();
        save.add(List.of("update_time", status_update_time)); // 실시간
        for (List<String> tmp : code) {
            save.add(loop(tmp, market));
        }
        return save;
    }
    private static List<String> loop(List<String> line, String market){
        for(int j = 2012; j <= 2022; j++) {
            for (int k = 0; k < 4; k++) {
                if(j == end_year && k == end_half){
                    if(k - 1 < 0) {
                        return List.of(line.get(1), j - 1 + "-11011");
                    }
                    return List.of(line.get(1),j + "-" + half[k-1]);
                }
                String year_w = Integer.toString(j);
                //
                if(!Files.exists(Path.of(route_for_dart_code + market + "\\" + line.get(1) + "\\"
                        + line.get(1) + "_" + year_w + "_" + half[k] + "_" + "OFS" + ".csv"))){
                    if(k - 1 < 0) {
                        return List.of(line.get(1), j - 1 + "-11011");
                    }
                    return List.of(line.get(1),j + "-" + half[k-1]);
                }
            }
        }
        return null;
    }
}
