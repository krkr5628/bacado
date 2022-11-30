import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Main {
     public static List<List<String>> stock_code = new ArrayList<>();
     public static void main(String[] args) throws IOException, ParseException {
        price_financial new_price_financial = new price_financial();
        stock_code_series();
        for(int i = 1; i < stock_code.size(); i++){
            List<String> tmp = stock_code.get(i);
            new_price_financial.price_financial(tmp.get(0), tmp.get(1));
            //System.out.println(tmp.get(0) + " " + tmp.get(1) + " " + tmp.get(2));
        }
         String[] ifrs_list = price_financial.jong_bok.toArray(new String[0]);
         writeCSV("IFRS_중복제거", ifrs_list);
    }
    private static void stock_code_series() throws FileNotFoundException {
        List<List<String>> csv_List = new ArrayList<>();
        File csv = new File("D:\\Drive\\Code\\bacado\\csv\\STOCK_CODE.csv");
        BufferedReader br = null;
        String line = "";
        //
        try{
            br = new BufferedReader((new FileReader(csv)));
            // readLine()은 파일에서 개행된 한줄의 데이터를 읽어온다
            while((line = br.readLine()) != null){
                List<String> aLine = new ArrayList<>();
                // 파일의 한 줄을 ,로 나누어 배열에 저장 후 리스트로 변환
                String[] lineArr = line.split(",");
                aLine = Arrays.asList(lineArr);
                csv_List.add(aLine);
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
                e.printStackTrace();;
            }
        }
        //
        stock_code = csv_List;
    }
    private static void writeCSV(String File_name, String[] ifrs){
         File csv = new File("D:\\Drive\\Code\\bacado\\csv\\" + File_name + ".csv");
         BufferedWriter bw = null;
        try{
            bw = new BufferedWriter((new FileWriter(csv)));
            // 덮어쓰기 true
            for(int i = 0; i < ifrs.length; i++){
                bw.write(ifrs[i]); //
                bw.newLine(); // 개행
                //System.out.println(tmp.get(0) + " " + tmp.get(1) + " " + tmp.get(2));
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
                e.printStackTrace();;
            }
        }
    }
}
