import java.io.IOException;
import java.util.List;

public class ParseImportantWordsTest {

    public static void main(String[] args) throws IOException {
        //Test the crawler for getting nested links
        //Should print out n-1 other links along with passed link for total of n
        Crawler crawler = new Crawler(4);
        crawler.startCrawl("https://courses.cs.washington.edu/courses/cse331/20au/");
        List<String> listOfLinks = crawler.getLinkList();
        for(String url : listOfLinks) {
            System.out.println(url);
        }

        System.out.println();

        //Test the parser to get link text
        //Should print out size (in characters) of text
        Parser parser = new Parser(listOfLinks);
        List<String> parseList = parser.parseText();
        for(String s : parseList) {
            System.out.println(s.length());
        }


    }

}