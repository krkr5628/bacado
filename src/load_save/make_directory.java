package load_save;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class make_directory {
    private static final String read_route_for_kospi_code = "D:\\Drive\\Code\\bacado\\csv\\list\\kospi_integration.csv";
    private static final String read_route_for_kosdak_code = "D:\\Drive\\Code\\bacado\\csv\\list\\kosdak_integration.csv";
    private static List<List<String>> kospi_code;
    private static List<List<String>> kosdak_code;
    public static void Make_directory() throws IOException {
        kospi_code = CSV.readCSV(read_route_for_kospi_code);
        kosdak_code = CSV.readCSV(read_route_for_kosdak_code);
        int kospi_length = kospi_code.size();
        int kosdak_length = kosdak_code.size();
        for(int i = 0; i < kospi_length; i++){
            String path = "D:\\Drive\\Code\\bacado\\csv\\kospi\\" + kospi_code.get(i).get(1);
            if(!Files.exists(Path.of(path))){
                Files.createDirectories(Paths.get(path));
            }
        }
        for(int i = 0; i < kosdak_length ; i++){
            String path = "D:\\Drive\\Code\\bacado\\csv\\kosdak\\" + kosdak_code.get(i).get(1);
            if(!Files.exists(Path.of(path))) {
                Files.createDirectories(Paths.get(path));
            }
        }
    }
}