package load_save;

import java.io.File;
import java.util.List;

public class file_name_change {
    private static final int end_year = 2022;
    private static final int end_half = 3;
    private static final String[] half = {"11013", "11012", "11014","11011"};
    private static final String[] fs = {"CFS", "OFS"};
    private static final String read_route_for_dart_code = "D:\\Drive\\Code\\bacado\\csv\\list\\STOCK_CODE.csv";
    private static final String write_route_for_dart_code = "D:\\Drive\\Code\\bacado\\csv\\";
    public static void File_name_change(){
        List<List<String>> stock_code = CSV.readCSV(read_route_for_dart_code);
        for(int i = 1; i <= 200; i++){
            for(int j = 2012; j <= 2022; j++) {
                for (int k = 0; k < 4; k++) {
                    for(int t = 0; t <= 1; t++){
                        if(j == end_year && k == end_half) break;
                        String year_w = Integer.toString(j);
                        String half_w = Integer.toString(k);
                        List<String> tmp = stock_code.get(i);
                        File kospi_file = new File(write_route_for_dart_code + "kospi" + "\\" + tmp.get(0) + "\\"
                                + tmp.get(0) + "_" + year_w + "_" + half_w + "_" + fs[t] + ".csv");
                        File kospi_newfile = new File(write_route_for_dart_code + "kospi" + "\\" + tmp.get(0) + "\\"
                                + tmp.get(0) + "_" + year_w + "_" + half[k] + "_" + fs[t] + ".csv");
                        boolean result = kospi_file.renameTo(kospi_newfile);
                        System.out.println(result);
                    }
                }
            }
        }
    }
}
