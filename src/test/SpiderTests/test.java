import java.io.IOException;

public class test {

    public static void main(String[] args) throws IOException {
        Spider crawler = new Spider("https://courses.cs.washington.edu/courses/cse331/20au/");
        for(String url : crawler.linkList) {
            System.out.println(url);
        }
        Parser parser = new Parser(crawler.linkList);
        parser.parseText();
    }
}