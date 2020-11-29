import java.util.*;

public class TF_IDF {

    List<String> texts;
    int count;

    public TF_IDF(List<String> texts) {
        this.texts = texts;
        this.count = texts.size();
    }

    public List<Map<String, Integer>> wordCountAcrossDocs() {
        List<Map<String, Integer>> allDocsWC = new ArrayList<>();

        for(String s : texts) {
            Map<String, Integer> wordCount = new HashMap<>();

            String[] splitWords = s.split(" ");
            for(String w : splitWords) {
                int value = wordCount.getOrDefault(w, 1);
                wordCount.put(w, value);
            }
            allDocsWC.add(wordCount);
        }
        return allDocsWC;
    }

    private double normIDF(List<Map<String, Integer>> allDocsWC, String word) {
        int contains = 0;
        for(Map<String, Integer> d : allDocsWC) {

        }
    }

    private double rawTF(List<Map<String, Integer>> allDocsWC, String word, int doc) {
        return allDocsWC.get(doc).get(word);
    }

    public double calculateRawTFNormIDF(String word) {
        List<Map<String, Integer>> allDocsWC = wordCountAcrossDocs();
        return rawTF(allDocsWC, word, 0) * normIDF(allDocsWC, word);
    }

}
