import export.ifrs_financial;
import initial.dart_code_update;
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
    private static final String write_route_for_dart_date = "D:\\Drive\\Code\\bacado\\csv\\";
    public static void main(String[] args) throws IOException, ParseException, ParserConfigurationException, SAXException {
        dart_code_update.Dart_code_update();

        //
        ifrs_financial new_ifrs_financial = new ifrs_financial();
        CSV.readCSV(read_route_for_dart_code);
        //KOSPI_200, KOSDAK_150
        for(int i = 1; i <= 5; i++){
            for(int j = 2012; j <= 2022; j++) {
                for (int k = 0; k < 4; k++) {
                    if(j == end_year && k == end_half) break;
                    String year_w = Integer.toString(j);
                    String half_w = Integer.toString(k);
                    List<String> tmp = CSV.stock_code.get(i);
                    //
                    new_ifrs_financial.download_financial(tmp.get(0), year_w, half[k], "CFS");
                    CSV.writeCSV(write_route_for_dart_date + "kospi" + "\\" + tmp.get(0) + "\\"
                            + tmp.get(0) + "_" + year_w + "_" + half_w + "_" + "CFS",
                            new_ifrs_financial.financial_save);
                    //
                    new_ifrs_financial.download_financial(tmp.get(0), year_w, half[k], "OFS");
                    CSV.writeCSV(write_route_for_dart_date + "kospi" + "\\" + tmp.get(0) + "\\"
                                    + tmp.get(0) + "_" + year_w + "_" + half_w + "_" + "OFS",
                            new_ifrs_financial.financial_save);
                    //
                    if (i <= 5) {
                        new_ifrs_financial.download_financial(tmp.get(3), year_w, half[k], "CFS");
                        CSV.writeCSV(write_route_for_dart_date + "kosdak" + "\\" + tmp.get(3) + "\\"
                            + tmp.get(3) + "_" + year_w + "_" + half_w + "_" + "CFS",
                                new_ifrs_financial.financial_save);
                        //
                        new_ifrs_financial.download_financial(tmp.get(3), year_w, half[k], "OFS");
                        CSV.writeCSV(write_route_for_dart_date + "kosdak" + "\\" + tmp.get(3) + "\\"
                            + tmp.get(3) + "_" + year_w + "_" + half_w + "_" + "CFS",
                                new_ifrs_financial.financial_save);
                    }

                }
            }
        }

    }
}
