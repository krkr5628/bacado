package load_save;

import java.util.HashMap;
import java.util.List;

public class ListToHashMap {
    public static HashMap<String, String> listTohashMap(List<List<String>> input_list, int key, int  value){
        HashMap<String, String> tmp = new HashMap<>();
        for(List<String> line : input_list){
            tmp.put(line.get(key), line.get(value));
        }
        return tmp;
    }
}
