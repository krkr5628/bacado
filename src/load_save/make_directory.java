package load_save;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class make_directory {
    private static final String read_route_for_kospi_code = "D:\\Drive\\Code\\bacado\\csv\\list\\kospi_integration.csv";
    private static final String read_route_for_kosdak_code = "D:\\Drive\\Code\\bacado\\csv\\list\\kosdak_integration.csv";

    public static void Make_directory() throws IOException {
        List<List<String>> kospi_code = CSV.readCSV(read_route_for_kospi_code);
        List<List<String>> kosdak_code = CSV.readCSV(read_route_for_kosdak_code);
        //
        for (List<String> strings : kospi_code) {
            String path = "D:\\Drive\\Code\\bacado\\csv\\kospi\\" + strings.get(1);
            if (!Files.exists(Path.of(path))) {
                Files.createDirectories(Paths.get(path));
            }
        }
        for (List<String> strings : kosdak_code) {
            String path = "D:\\Drive\\Code\\bacado\\csv\\kosdak\\" + strings.get(1);
            if (!Files.exists(Path.of(path))) {
                Files.createDirectories(Paths.get(path));
            }
        }
    }
}