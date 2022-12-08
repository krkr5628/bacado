package export;

import load_save.CSV;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class dart_code {
    public static void xmltocsv(String file_route, String save_route) throws ParserConfigurationException, IOException, SAXException {
        List<List<String>> tmp = new ArrayList<>();
        File file = new File(file_route);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(file);
        document.getDocumentElement().normalize();
        NodeList nList = document.getElementsByTagName("list");
        for(int cnt = 0; cnt < nList.getLength(); cnt++){
            Node nNode = nList.item(cnt);
            Element eElement = (Element) nNode;
            tmp.add(List.of(eElement.getElementsByTagName("corp_name").item(0).getTextContent(),
                    eElement.getElementsByTagName("corp_code").item(0).getTextContent()));
        }
        CSV.writeCSV(save_route, tmp);
    }
}
