import java.io.IOException;
import java.util.*;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {

    List<String> linksList;
    int maxLinks;

    public Crawler(int max) {
        this.maxLinks = max;
        this.linksList = new ArrayList<>();
    }

    public Crawler() {
        this.maxLinks = 1;
        this.linksList = new ArrayList<>();
    }

    public List<String> getLinkList() {
        return this.linksList;
    }

    public void setLinkList(List<String> list) {
        this.linksList = list;
    }

    public int getMaxLinks() {
        return this.maxLinks;
    }

    public void setMaxLinks(int max) {
        this.maxLinks = max;
    }

    public void startCrawl(String url) {
        Set<String> visited = new HashSet<>();
        Queue<String> toVisit = new LinkedList<>();
        List<String> list = new ArrayList<>();

        toVisit.add(url);
        visited.add(url);
        list.add(url);

        while(!toVisit.isEmpty() && list.size() < maxLinks) {
            String currentUrl = toVisit.remove();

            List<String> innerLinks = innerLinkCrawl(currentUrl);
            for(String link : innerLinks) {
                if(visited.size() >= maxLinks) {
                    break;
                }

                if(!visited.contains(link)) {
                    toVisit.add(link);
                    visited.add(link);
                    list.add(link);
                }
            }
        }

        this.linksList = list;
    }

    private List<String> innerLinkCrawl(String url) {
        List<String> links = new ArrayList<>();
        try {
            Connection conn = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows; U; Windows NT 6.1; rv:2.2) Gecko/20110201");
            Document doc = conn.get();

            Elements pageLinks = doc.select("a[href]");
            for (Element link : pageLinks) {
                links.add(link.absUrl("href"));
            }
            return links;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return links;
    }
}
