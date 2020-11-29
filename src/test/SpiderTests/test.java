import java.io.IOException;
import java.util.List;

public class test {

    public static void main(String[] args) throws IOException {
        Crawler crawler = new Crawler("https://courses.cs.washington.edu/courses/cse331/20au/", 1);
        for(String url : crawler.linkList) {
            System.out.println(url);
        }
        Parser parser = new Parser(crawler.linkList);
        List<String> parseList = parser.parseText();
        System.out.println(parseList.size());
        for(String s : parseList) {
            System.out.println(s.length());
        }
    }
}