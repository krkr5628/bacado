import initial.initial_update;
import initial.ifrs_update;
import load_save.CSV;

import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    private static final String read_route_for_kospi_update_list = "D:\\Drive\\Code\\bacado\\csv\\list\\kospi_update_list.csv";
    private static final String read_route_for_kosdak_update_list = "D:\\Drive\\Code\\bacado\\csv\\list\\kosdak_update_list.csv";
    private static final String[] half = {"11013", "11012", "11014", "11011"};

    public static void main(String[] args) throws IOException, ParseException, ParserConfigurationException, SAXException {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //code_standarzation.Code_standarzation();
                //initial_update.Code_update();
                //ifrs_update.download_financial(CSV.readCSV(read_route_for_kospi_update_list), "kospi");
                //ifrs_update.download_financial(CSV.readCSV(read_route_for_kosdak_update_list), "kosdak");
            }
        };
        new Timer().scheduleAtFixedRate(task, 1000, 300000);
    }
}
