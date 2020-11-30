import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordCounter {

    List<String[]> allDocsSplit;
    List<Map<String, Integer>> allDocsWC;

    public WordCounter(List<String> texts) {
        this.allDocsSplit = splitWords(texts);
        this.allDocsWC = mapAllDocWordsToCount(allDocsSplit);
    }

    private List<String[]> splitWords(List<String> texts) {
        List<String[]> allDocsSplit = new ArrayList<>();

        for(String s : texts) {
            s = s.trim().replaceAll("[^a-zA-Z0-9]", " ").toLowerCase();
            String[] split = s.split("\\s+");
            allDocsSplit.add(split);
        }
        return allDocsSplit;
    }

    private List<Map<String, Integer>> mapAllDocWordsToCount(List<String[]> allDocsSplit) {
        List<Map<String, Integer>> allDocsWC = new ArrayList<>();

        for(String[] sw : allDocsSplit) {
            Map<String, Integer> wordCount = new HashMap<>();
            for (String w : sw) {
                int value = wordCount.getOrDefault(w, 0);
                wordCount.put(w, value + 1);
            }
            allDocsWC.add(wordCount);
        }
        return allDocsWC;
    }

    public List<String[]> getAllDocsSplit() {
        return this.allDocsSplit;
    }

    public List<Map<String, Integer>> getAllDocsWC() {
        return this.allDocsWC;
    }
}
