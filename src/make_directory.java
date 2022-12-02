import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class make_directory {
    public static void Make_directory(List<List<String>> total_file) throws IOException {
        for(int i = 1; i <= 200; i++){
            List<String> tmp = CSV.stock_code.get(i);
            Files.createDirectories(Paths.get("D:/Drive/Code/bacado/csv/kospi/" + tmp.get(0)));
            if (i <= 150) {
                Files.createDirectories(Paths.get("D:\\Drive\\Code\\bacado\\csv\\kosdak\\" + tmp.get(3)));
            }
        }
    }
}
