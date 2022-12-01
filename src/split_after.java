import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class split_after {
    private List<List<String>> go = new ArrayList<>();
    public List<List<String>> split_after(HashSet tmp){
        Iterator iter = tmp.iterator();
        while(iter.hasNext()){
            String[] after = iter.next().toString().split(",");
            go.add(List.of(after[0],after[1],after[2], after[3]));
        }
        return go;
    }
}
