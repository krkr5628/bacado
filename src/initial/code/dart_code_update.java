package initial.code;

import load_save.CSV;
import static initial.setting.dart_api_key;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class dart_code_update {
    private static final String testDir = "D:\\Drive\\Code\\bacado\\";
    private static final String file_name_for_dart_code = "CORPCODE.xml";
    private static final String load_route_for_dart_code_xml = "D:\\Drive\\Code\\bacado\\CORPCODE.xml";
    private static final String save_route_for_dart_code = "D:\\Drive\\Code\\bacado\\csv\\list\\dart_code.csv";
    public static List<List<String>> dart_list = new ArrayList<>();
    public static void Dart_code_update() throws IOException, ParserConfigurationException, SAXException {
        String url_plus1 = "https://opendart.fss.or.kr/api/corpCode.xml?crtfc_key=" + dart_api_key;
        URL url1 = new URL(url_plus1);
        //
        InputStream inputStream = new ByteArrayInputStream(url1.openStream().readAllBytes());
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        ZipEntry zipEntry;
        //
        Path path = Path.of(testDir + file_name_for_dart_code);
        Files.deleteIfExists(path);
        //
        while((zipEntry = zipInputStream.getNextEntry()) != null){
            Files.copy(zipInputStream, Paths.get(testDir + zipEntry.getName()));
        }
        //
        zipInputStream.closeEntry();
        zipInputStream.close();
        //
        xmltocsv();
    }
    private static void xmltocsv() throws ParserConfigurationException, IOException, SAXException {
        File file = new File(load_route_for_dart_code_xml);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(file);
        document.getDocumentElement().normalize();
        NodeList nList = document.getElementsByTagName("list");
        for(int cnt = 0; cnt < nList.getLength(); cnt++){
            Node nNode = nList.item(cnt);
            Element eElement = (Element) nNode;
            dart_list.add(List.of(eElement.getElementsByTagName("corp_code").item(0).getTextContent(),
                    eElement.getElementsByTagName("corp_name").item(0).getTextContent()));
        }
        CSV.writeCSV(save_route_for_dart_code, dart_list);
    }
}
