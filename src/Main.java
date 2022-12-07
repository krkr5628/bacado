import export.ifrs_financial;
import initial.dart_code_update;
import initial.short_code_update;
import load_save.CSV;

import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.List;

public class Main {
    private static final int end_year = 2022;
    private static final int end_half = 3;
    private static final String[] half = {"11013", "11012", "11014","11011"};
    private static final String read_route_for_dart_code = "D:\\Drive\\Code\\bacado\\csv\\list\\STOCK_CODE.csv";
    public static void main(String[] args) throws IOException, ParseException, ParserConfigurationException, SAXException {
        short_code_update.Short_code_update();
        dart_code_update.Dart_code_update();

        ifrs_financial new_ifrs_financial = new ifrs_financial();
        CSV.readCSV(read_route_for_dart_code);
        //
        for(int i = 1; i <= 3; i++){
            for(int j = 2012; j <= 2022; j++) {
                for (int k = 0; k < 4; k++) {
                    if(j == end_year && k == end_half) break;
                    String year_w = Integer.toString(j);
                    List<String> tmp = CSV.stock_code.get(i);
                    //
                    new_ifrs_financial.download_financial(tmp.get(0), year_w, half[k], "CFS", "kospi");
                    new_ifrs_financial.download_financial(tmp.get(0), year_w, half[k], "OFS", "kospi");
                    //
                    if (i <= 3) {
                        new_ifrs_financial.download_financial(tmp.get(3), year_w, half[k], "CFS", "kosdak");
                        new_ifrs_financial.download_financial(tmp.get(3), year_w, half[k], "OFS", "kosdak");
                    }

                }
            }
        }


    }
}
