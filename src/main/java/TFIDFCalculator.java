import java.util.*;

public class TFIDFCalculator {

    List<String> texts;
    WordCounter counter;

    public TFIDFCalculator(List<String> texts) {
        this.texts = texts;
        counter = new WordCounter(texts);
    }

    public double RegIDF(String word) {
        double contains = 0;
        List<Map<String, Integer>> map = counter.getAllDocsWC();
        for(Map<String, Integer> d : map) {
            if(d.containsKey(word)) {
                contains++;
            }
        }

        double size = map.size();
        return Math.log(size/contains);
    }

    public double smoothIDF(String word) {
        double contains = 0;
        List<Map<String, Integer>> map = counter.getAllDocsWC();
        for(Map<String, Integer> d : map) {
            if(d.containsKey(word)) {
                contains++;
            }
        }

        double size = map.size();
        return Math.log(size/(contains+1)) + 1;
    }

    public double maxIDF(int selectedDoc, String word) {
        double contains = 0;
        List<Map<String, Integer>> list = counter.getAllDocsWC();
        Map<String, Integer> map1 = list.get(selectedDoc);
        double maxOccurTerm = Collections.max(map1.values());

        List<Map<String, Integer>> map2 = counter.getAllDocsWC();
        for(Map<String, Integer> d : map2) {
            if(d.containsKey(word)) {
                contains++;
            }
        }

        return Math.log(maxOccurTerm/(contains+1));
    }

    public double probabilisticIDF(String word) {
        double contains = 0;
        List<Map<String, Integer>> map = counter.getAllDocsWC();
        for(Map<String, Integer> d : map) {
            if(d.containsKey(word)) {
                contains++;
            }
        }

        double size = map.size();
        return Math.log((size-contains)/contains);
    }





    public double RawTF(int selectedDoc, String word) {
        List<Map<String, Integer>> list = counter.getAllDocsWC();
        Map<String, Integer> map = list.get(selectedDoc);
        return (double)map.get(word);
    }

    public double RegTF(int selectedDoc, String word) {
        List<String[]> list = counter.getAllDocsSplit();
        String[] array = list.get(selectedDoc);
        double value = array.length;

        return RawTF(selectedDoc, word)/value;
    }

    public double LogNormTF(int selectedDoc, String word) {
        return Math.log(RawTF(selectedDoc, word) + 1);
    }

    public double doubleNormHalfValTF(int selectedDoc, String word) {
        List<Map<String, Integer>> list = counter.getAllDocsWC();
        Map<String, Integer> map = list.get(selectedDoc);
        double maxOccurTerm = Collections.max(map.values());

        return 0.5 + (0.5 * (RawTF(selectedDoc, word)/maxOccurTerm));
    }

    public double doubleNormKValTF(int selectedDoc, String word, double k) {
        List<Map<String, Integer>> list = counter.getAllDocsWC();
        Map<String, Integer> map = list.get(selectedDoc);
        double maxOccurTerm = Collections.max(map.values());

        return k + (k * (RawTF(selectedDoc, word)/maxOccurTerm));
    }

}
