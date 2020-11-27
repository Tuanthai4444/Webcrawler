import java.util.*;
import org.jsoup.*;

public class spider {

    private final int MAX_LINKS = 10;

    Set<String> visited;
    Queue<String> toVisit;

    public spider() {
        visited = new HashSet<String>();
        toVisit = new LinkedList<String>();
    }

    public void startCrawl(String url, String word) {

        toVisit.add(url);
        visited.add(url);
        while(!toVisit.isEmpty() || visited.size() == MAX_LINKS) {
            
        }
    }

}
