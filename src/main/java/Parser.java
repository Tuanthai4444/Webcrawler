import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    List<String> workList;

    public Parser(List<String> links) {
        workList = links;
    }

    public List<String> parseText() throws IOException {
        List<String> textTranscripts = new ArrayList<>();
        for(String url : workList) {
            Connection conn = Jsoup.connect(url).
                    userAgent("Mozilla/5.0 (Windows; U; Windows NT 6.1; rv:2.2) Gecko/20110201");
            Document doc = conn.get();

            Elements element = doc.select("p");
            for (Element t : element) {
                textTranscripts.add(t.text());
            }
        }
        return textTranscripts;
    }




}
