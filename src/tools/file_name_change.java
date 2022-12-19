package tools;

import load_save.CSV;

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
        List<List<String>> stock_code = CSV.readCSV(save_route_for_kospi_integration);
        for(int i = 0; i < 200; i++){
            for(int j = 2012; j <= 2022; j++) {
                String year_w = Integer.toString(j);
                for (int k = 0; k < 4; k++) {
                    for(int t = 0; t <= 1; t++){
                        Path tmp = Paths.get(write_route_for_dart_code + "kospi" + "\\" + stock_code.get(i).get(1) + "\\"
                                + stock_code.get(i).get(1) + "_financial_history.csv");
                        Path tmp2 = Paths.get(write_route_for_dart_code + "kospi" + "\\" + stock_code.get(i).get(1) + "\\"
                                + stock_code.get(i).get(1) + "_financial_company.csv");

                        if(Files.exists(tmp)){
                            File file = new File(tmp.toUri());
                            File new_file = new File(tmp2.toUri());
                            boolean result = file.renameTo(new_file);
                            System.out.println(result);
                        }
                        if(j == end_year && k == end_half) break;
                    }
                }
            }
        }
    }
}
