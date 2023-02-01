import initial.ifrs_update;
import initial.initial_update;
import load_save.CSV;

import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {
    private static final String read_route_for_kospi_update_list = "D:\\Drive\\Code\\bacado\\csv\\list\\kospi_update_list.csv";
    private static final String read_route_for_kosdak_update_list = "D:\\Drive\\Code\\bacado\\csv\\list\\kosdak_update_list.csv";
    private static final String[] half = {"11013", "11012", "11014", "11011"};

    public static void main(String[] args) throws IOException, ParseException, ParserConfigurationException, SAXException, InterruptedException {
        initial_update.Code_update();
        //code_standarzation.Code_standarzation();
        /*
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    initial_update.Code_update();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ParserConfigurationException e) {
                    throw new RuntimeException(e);
                } catch (SAXException e) {
                    throw new RuntimeException(e);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        new Timer().scheduleAtFixedRate(task, 1000, 86400000); //1day
        */
    }
}
