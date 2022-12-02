import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.List;

public class Main {
    private static final int end_year = 2022;
    private static final int end_half = 3;
    private static final String[] half = {"11013", "11012", "11014","11011"};
     public static void main(String[] args) throws IOException, ParseException {
        price_financial new_price_financial = new price_financial();
        CSV.readCSV();
        //make_directory.Make_directory(CSV.stock_code);
        //KOSPI_200, KOSDAK_150
        for(int i = 1; i <= 200; i++){
            for(int j = 2012; j <= 2022; j++) {
                for (int k = 0; k < 4; k++) {
                    if(j == end_year && k == end_half) break;
                    String year_w = Integer.toString(j);
                    String half_w = Integer.toString(k);
                    List<String> tmp = CSV.stock_code.get(i);
                    //
                    new_price_financial.Price_financial(tmp.get(0), year_w, half[k], "CFS");
                    CSV.writeCSV("kospi", tmp.get(0), year_w, half_w,"CFS", new_price_financial.financial_save);
                    new_price_financial.Price_financial(tmp.get(0), year_w, half[k], "OFS");
                    CSV.writeCSV("kospi", tmp.get(0), year_w, half_w,"OFS", new_price_financial.financial_save);
                    /*
                    if (i <= 150) {
                        new_price_financial.Price_financial(tmp.get(3), year_w, half[k], "CFS");
                        CSV.writeCSV("kosdak", tmp.get(3), year_w, half_w,"CFS", new_price_financial.financial_save) ;
                        new_price_financial.Price_financial(tmp.get(3), year_w, half[k], "OFS");
                        CSV.writeCSV("kosdak", tmp.get(3), year_w, half_w,"OFS", new_price_financial.financial_save);
                    }
                    */
                }
            }
        }
    }
}
