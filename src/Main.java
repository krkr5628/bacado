import export.ifrs_financial;
import initial.code_update;
import load_save.CSV;

import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.List;

public class Main {
    private static final String read_route_for_kospi_code = "D:\\Drive\\Code\\bacado\\csv\\list\\kospi_integration.csv";
    private static final String read_route_for_kosdak_code = "D:\\Drive\\Code\\bacado\\csv\\list\\kosdak_integration.csv";
    private static List<List<String>> kospi_code;
    private static List<List<String>> kosdak_code;
    private static final String[] half = {"11013", "11012", "11014","11011"};
    private static final int end_year = 2022; // 업데이트 해줘야함
    private static final int end_half = 3; // 업데이트 해줘야함
    public static void main(String[] args) throws IOException, ParseException, ParserConfigurationException, SAXException {

        /*
        code_update.Code_update();
        //
        kospi_code = CSV.readCSV(read_route_for_kospi_code);
        kosdak_code = CSV.readCSV(read_route_for_kosdak_code);
        //
        download();
        */
    }
    private static void download() throws IOException, ParseException {
        for(int i = 1; i <= 3; i++){
            for(int j = 2019; j <= 2022; j++) {
                for (int k = 0; k < 4; k++) {
                    if(j == end_year && k == end_half) break;
                    String year_w = Integer.toString(j);
                    //
                    List<String> tmp = kospi_code.get(i);
                    List<String> tmp2 = kosdak_code.get(i);
                    //
                    ifrs_financial.download_financial(tmp.get(1), year_w, half[k], "CFS", "kospi");
                    ifrs_financial.download_financial(tmp.get(1), year_w, half[k], "OFS", "kospi");
                    //
                    if (i <= 3) {
                        ifrs_financial.download_financial(tmp2.get(1), year_w, half[k], "CFS", "kosdak");
                        ifrs_financial.download_financial(tmp2.get(1), year_w, half[k], "OFS", "kosdak");
                    }

                }
            }
        }
    }
}
