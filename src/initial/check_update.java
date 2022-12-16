package initial;

import load_save.CSV;

import java.util.List;

public class check_update {

    public static boolean Check_update(String route){
        List<List<String>> update_log = CSV.readCSV(route);

        return false;
    }

}
