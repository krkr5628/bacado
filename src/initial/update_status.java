package initial;

import load_save.CSV;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static initial.setting.end_half;
import static initial.setting.end_year;

public class update_status {
    private static final String[] half = {"11013", "11012", "11014","11011"};
    private static final String read_route_for_kospi_integration = "D:\\Drive\\Code\\bacado\\csv\\list\\kospi_integration.csv";
    private static final String read_route_for_kosdak_integration = "D:\\Drive\\Code\\bacado\\csv\\list\\kosdak_integration.csv";
    private static final String route_for_dart_code = "D:\\Drive\\Code\\bacado\\csv\\";
    private static final String save_route_for_kospi = "D:\\Drive\\Code\\bacado\\csv\\list\\kospi_status.csv";
    private static final String save_route_for_kodak = "D:\\Drive\\Code\\bacado\\csv\\list\\kosdak_status.csv";
    public static void Update_list(){
        List<List<String>> kospi = CSV.readCSV(read_route_for_kospi_integration);
        List<List<String>> kosdak = CSV.readCSV(read_route_for_kosdak_integration);
        CSV.writeCSV(save_route_for_kospi,update(kospi, "kospi"));
        CSV.writeCSV(save_route_for_kodak,update(kosdak, "kosdak"));
    }
    private static List<List<String>> update(List<List<String>> code, String market){
        List<List<String>> save = new ArrayList<>();
        save.add(List.of("update_time","2022-12-09")); // 실시간
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
