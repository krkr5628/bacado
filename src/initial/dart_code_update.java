package initial;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class dart_code_update {
    private static final String key = ""; // 입력하고 개발
    private static final String testDir = "D:\\Drive\\Code\\bacado\\";
    public List<List<String>> financial_save;
    public static void Dart_code_update() throws IOException {
        String url_plus1 = "https://opendart.fss.or.kr/api/corpCode.xml?crtfc_key=" + key;
        URL url1 = new URL(url_plus1);
        //
        InputStream inputStream = new ByteArrayInputStream(url1.openStream().readAllBytes());
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        ZipEntry zipEntry = null;
        //
        while((zipEntry = zipInputStream.getNextEntry()) != null){
            Files.copy(zipInputStream, Paths.get(testDir + zipEntry.getName()));
        }
        //
        zipInputStream.closeEntry();;
        zipInputStream.close();
        //
    }
}
