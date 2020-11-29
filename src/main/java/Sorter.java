import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Sorter {

    private final int SELECTED_DOC = 0;
    private final double K_VAL = 0.4;

    private final WordCounter counter;

    public Sorter(List<String> texts) {
        this.counter = new WordCounter(texts);
    }

    public Map<Double, String> sort() {
        Map<Double, String> ret = new TreeMap<>();
        Map<String, Integer> selectedDocWC = counter.getAllDocsWC().get(SELECTED_DOC);

        for(String word : selectedDocWC.keySet()) {
            TFIDF tfIdf = new TFIDF(counter);
            tfIdf.getIdf().SetRegIDF(word);
            tfIdf.getTf().SetDoubleNormKValTF(SELECTED_DOC, word, K_VAL);
            double wordVal = tfIdf.getTFIDF();
            ret.put(wordVal, word);
        }
        return ret;
    }
}