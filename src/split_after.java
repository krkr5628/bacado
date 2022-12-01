import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class split_after {
    private List<List<String>> go = new ArrayList<>();
    public List<List<String>> Split_after(HashSet<String> tmp){
        for (String s : tmp) {
            String[] after = s.split(",");
            go.add(List.of(after[0], after[1], after[2], after[3]));
        }
        return go;
    }
}
