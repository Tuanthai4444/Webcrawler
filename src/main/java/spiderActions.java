import java.io.IOException;
import java.util.*;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class spiderActions {

    private Document doc;
    private List<String> links;

    public List<String> getLinks() {
        return this.links;
    }

    public boolean captureLinks(String url) throws IOException {
        Connection conn = Jsoup.connect(url)
                            .userAgent("Mozilla/5.0 (Windows; U; Windows NT 6.1; rv:2.2) Gecko/20110201");

        this.doc = conn.get();
        links = new ArrayList<String>();

        if(conn.response().statusCode() == 200) {
            if(!conn.response().contentType().contains("text/html")) {
                return false;
            } else {
                Elements pageLinks = doc.select("a[href]");
                for(Element link : pageLinks) {
                    this.links.add(link.absUrl("href"));
                }
            }
            return true;
        }
        return false;
    }

    
}
