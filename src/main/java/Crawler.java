import java.io.IOException;
import java.util.*;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {

    List<String> linkList;
    int maxLinks;

    public Crawler(String url, int max) {
        try {
            this.maxLinks = max;
            this.linkList = linkCrawl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Crawler(String url) {
        try {
            this.maxLinks = 1;
            this.linkList = linkCrawl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getLinkList() {
        return this.linkList;
    }

    public void setLinkList(List<String> list) {
        this.linkList = list;
    }

    public int getMaxLinks() {
        return this.maxLinks;
    }

    public void setMaxLinks(int max) {
        this.maxLinks = max;
    }

    private List<String> linkCrawl(String url) throws IOException {
        Set<String> visited = new HashSet<>();
        Queue<String> toVisit = new LinkedList<>();
        List<String> linkList = new ArrayList<>();

        toVisit.add(url);
        visited.add(url);
        linkList.add(url);

        while(!toVisit.isEmpty() && linkList.size() < maxLinks) {
            String currentUrl = toVisit.remove();

            List<String> innerLinks = innerLinkCrawl(currentUrl);
            for(String link : innerLinks) {
                if(visited.size() > maxLinks) {
                    break;
                }

                if(!visited.contains(link)) {
                    toVisit.add(link);
                    visited.add(link);
                    linkList.add(link);
                }
            }
        }

        return linkList;
    }

    private List<String> innerLinkCrawl(String url) throws IOException {
        List<String> links = new ArrayList<>();
        Connection conn = Jsoup.connect(url)
                            .userAgent("Mozilla/5.0 (Windows; U; Windows NT 6.1; rv:2.2) Gecko/20110201");
        Document doc = conn.get();

        Elements pageLinks = doc.select("a[href]");
        for(Element link : pageLinks) {
            links.add(link.absUrl("href"));
        }
        return links;
    }
}
