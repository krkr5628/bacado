package initial.code;

import load_save.CSV;
import load_save.ListToHashMap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static initial.setting.standard_date;

public class code_integration {
    public static void Code_integration(List<List<String>> dart_code, List<List<String>> short_code, String market,
                                        String save_route_for_integration, String save_route_for_new, String save_route_for_left) throws IOException {
        List<List<String>> integration = new ArrayList<>(); // dart_code와 호환되는 krx_code
        List<List<String>> integration_left = new ArrayList<>(); // dart_code와 호환불가한 krx_code
        List<List<String>> integration_new = new ArrayList<>(); // dart_code와 호환되는 새로운 krx_code
        integration_new.add(List.of(standard_date));
        //
        HashMap<String, String> dart_code_hashmap = ListToHashMap.listTohashMap(dart_code, 1, 0); // dart_code hash_map
        HashMap<String, String> old_integration_code_hashmap = ListToHashMap.listTohashMap(CSV.readCSV(save_route_for_integration), 0, 2);
        for(List<String> tmp : short_code){
            if(dart_code_hashmap.containsKey(tmp.get(2))){
                // 매칭된 모든 종목 추가
                integration.add(List.of(tmp.get(0), dart_code_hashmap.get(tmp.get(2)), tmp.get(2)));
                // 매칭된 것 중에서 신규 종목
                if(!old_integration_code_hashmap.containsKey(tmp.get(0))){
                    integration_new.add(List.of(tmp.get(0),tmp.get(1),"NEW"));
                    // 신규 종목 디렉토리 생성
                    String path = "D:\\Drive\\Code\\bacado\\csv\\" + market + "\\" + dart_code_hashmap.get(tmp.get(2));
                    if (!Files.exists(Path.of(path))) {
                        Files.createDirectories(Paths.get(path));
                    }
                }
            }
            else{
                //매칭 불가능한 것
                //코드가 존재하면서 이름이 다른것
                if(old_integration_code_hashmap.containsKey(tmp.get(0))){
                    integration_new.add(List.of(tmp.get(0),tmp.get(1),"NAME"));
                }
                else{
                    //코드가 존재하지 않으면서 이름도 다른것
                    integration_left.add(List.of(tmp.get(0),tmp.get(1)));
                }
            }
        }
        CSV.writeCSV(save_route_for_integration, integration);
        CSV.writeCSVoverride(save_route_for_new, integration_new);
        CSV.writeCSV(save_route_for_left, integration_left);
    }

}
