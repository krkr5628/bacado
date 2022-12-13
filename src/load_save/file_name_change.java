package load_save;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class file_name_change {
    private static final int end_year = 2022;
    private static final int end_half = 3;
    private static final String[] half = {"11013", "11012", "11014","11011"};
    private static final String[] fs = {"CFS", "OFS"};
    private static final String save_route_for_kospi_integration = "D:\\Drive\\Code\\bacado\\csv\\list\\kospi_integration.csv";
    private static final String save_route_for_kosdak_integration = "D:\\Drive\\Code\\bacado\\csv\\list\\kosdak_integration.csv";
    private static final String write_route_for_dart_code = "D:\\Drive\\Code\\bacado\\csv\\";
    public static void File_name_change(){
        List<List<String>> stock_code = CSV.readCSV(save_route_for_kosdak_integration);
        for(int i = 0; i < 200; i++){
            for(int j = 2012; j <= 2022; j++) {
                String year_w = Integer.toString(j);
                for (int k = 0; k < 4; k++) {
                    for(int t = 0; t <= 1; t++){
                        Path tmp = Paths.get(write_route_for_dart_code + "kosdak" + "\\" + stock_code.get(i).get(1) + "\\"
                                + stock_code.get(i).get(1) + "_" + year_w + "_" + half[k] + "_" + fs[t]);
                        Path tmp2 = Paths.get(write_route_for_dart_code + "kosdak" + "\\" + stock_code.get(i).get(1) + "\\"
                                + stock_code.get(i).get(1) + "_" + year_w + "_" + half[k] + "_" + fs[t] + ".csv");
                        if(Files.exists(tmp)){
                            File kospi_file = new File(tmp.toUri());
                            File kospi_newfile = new File(tmp2.toUri());
                            boolean result = kospi_file.renameTo(kospi_newfile);
                            System.out.println(result);
                        }
                        if(j == end_year && k == end_half) break;
                    }
                }
            }
        }
    }
    private static String code_length(String tmp){
        String new_code = tmp;
        if(tmp.length() < 8){
            for(var i = 0; i < (8 - tmp.length()); i++){
                new_code = "0" + new_code;
            }
        }
        return new_code;
    }
}
