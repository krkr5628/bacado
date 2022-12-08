package initial;

import load_save.CSV;

import java.util.ArrayList;
import java.util.List;

public class update_list {
    private static final String read_route_for_kospi_status = "D:\\Drive\\Code\\bacado\\csv\\list\\kospi_status.csv";
    private static final String read_route_for_kosdak_status = "D:\\Drive\\Code\\bacado\\csv\\list\\kosdak_status.csv";
    private static final String save_route_for_kospi = "D:\\Drive\\Code\\bacado\\csv\\list\\kospi_update_list.csv";
    private static final String save_route_for_kodak = "D:\\Drive\\Code\\bacado\\csv\\list\\kosdak_update_list.csv";
    private static final String current_status = "2022-11014"; //3분기
    public static void Update_list(){
        List<List<String>> kospi = CSV.readCSV(read_route_for_kospi_status);
        List<List<String>> kosdak = CSV.readCSV(read_route_for_kosdak_status);
        check_update(kospi,save_route_for_kospi);
        check_update(kosdak,save_route_for_kodak);
    }
    private static void check_update(List<List<String>> line, String route){
        List<List<String>> save_file = new ArrayList<>();
        for(List<String> tmp : line){
            if(!tmp.get(1).equals(current_status)){
                save_file.add(tmp);
            }
        }
        CSV.writeCSV(route, save_file);
    }
}
