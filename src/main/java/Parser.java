import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public class Parser {

    List<String> workList;

    public Parser(List<String> links) {
        workList = links;
    }

    public void parseText() throws IOException {
        for(String url : workList) {
            Connection conn = Jsoup.connect(url).
                    userAgent("Mozilla/5.0 (Windows; U; Windows NT 6.1; rv:2.2) Gecko/20110201");
            Document doc = conn.get();

            Elements element = doc.select("p");
            for (Element t : element) {
                System.out.println(t.text());
            }
        }
    }


}
