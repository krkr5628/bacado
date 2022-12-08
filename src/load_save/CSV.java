package load_save;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSV {
    public static List<List<String>> readCSV(String route) {
        List<List<String>> stock_code = new ArrayList<>();
        File csv = new File(route);
        BufferedReader br = null;
        String line;
        //
        try{
            br = new BufferedReader((new FileReader(csv)));
            // readLine()은 파일에서 개행된 한줄의 데이터를 읽어온다
            while((line = br.readLine()) != null){
                List<String> aLine;
                // 파일의 한 줄을 ,로 나누어 배열에 저장 후 리스트로 변환
                String[] lineArr = line.split(",");
                aLine = Arrays.asList(lineArr);
                stock_code.add(aLine);
            }
        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        } finally {
            try{
                if(br != null){
                    br.close();
                }
            } catch(IOException e){
                e.printStackTrace();
            }
        }
        return stock_code;
    }
    public static void writeCSV(String route, List<List<String>> ifrs){
        File csv = new File(route);
        BufferedWriter bw = null;
        try{
            bw = new BufferedWriter((new FileWriter(csv)));
            // 덮어쓰기 true
            for (List<String> ifr : ifrs) {
                if(ifr.get(0).equals("금융회사")) break;
                bw.write(make_line(ifr));
                bw.newLine(); // 개행
            }
        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        } finally {
            try{
                if(bw != null){
                    bw.flush(); // 남아있는 데이터까지 보내준다.
                    bw.close(); // 사용한 bufferedWriter를 닫아 준다.
                }
            } catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    private static String make_line(List<String> file_line){
        StringBuilder one_line = new StringBuilder();
        int end_line = file_line.size();
        for(int cnt = 0; cnt < end_line - 1; cnt++){
            one_line.append(file_line.get(cnt)).append(",");
        }
        one_line.append(file_line.get(end_line - 1));
        return one_line.toString();
    }
}
