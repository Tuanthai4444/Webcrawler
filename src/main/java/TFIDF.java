import java.util.*;

public class TFIDF {

    private final WordCounter counter;
    private IDF idf;
    private TF tf;

    public TFIDF(WordCounter counter) {
        this.counter = counter;
        this.idf = new IDF();
        this.tf = new TF();
    }

    public IDF getIdf() {
        return this.idf;
    }

    public TF getTf() {
        return this.tf;
    }

    public double getTFIDF() {
        if(tf.getValue() == 0) {
           throw new IllegalArgumentException("Cannot Have A Zero TF");
        } else if (idf.getValue() == 0){
            throw new IllegalArgumentException("Cannot Have A Zero IDF");
        } else {
            return (this.tf.getValue() * this.idf.getValue());
        }
    }

    public class IDF {

        private double value;

        public IDF() {
            this.value = 0.0;
        }

        public double getValue() {
            return this.value;
        }

        public void SetRegIDF(String word) {
            double contains = 0;
            List<Map<String, Integer>> map = counter.getAllDocsWC();
            for(Map<String, Integer> d : map) {
                if(d.containsKey(word)) {
                    contains++;
                }
            }

            double size = map.size();
            this.value = Math.log(size/contains);
        }

        public void SetSmoothIDF(String word) {
            double contains = 0;
            List<Map<String, Integer>> map = counter.getAllDocsWC();
            for(Map<String, Integer> d : map) {
                if(d.containsKey(word)) {
                    contains++;
                }
            }

            double size = map.size();
            this.value = Math.log(size/(contains+1)) + 1;
        }

        public void SetMaxIDF(int selectedDoc, String word) {
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

            this.value = Math.log(maxOccurTerm/(contains+1));
        }

        public void SetProbabilisticIDF(String word) {
            double contains = 0;
            List<Map<String, Integer>> map = counter.getAllDocsWC();
            for(Map<String, Integer> d : map) {
                if(d.containsKey(word)) {
                    contains++;
                }
            }

            double size = map.size();
            this.value = Math.log((size-contains)/contains);
        }
    }

    public class TF {

        private double value;

        public TF() {
            this.value = 0;
        }

        public double getValue() {
            return this.value;
        }

        public double RetRawTF(int selectedDoc, String word) {
            List<Map<String, Integer>> list = counter.getAllDocsWC();
            Map<String, Integer> map = list.get(selectedDoc);
            return (double)map.get(word);
        }

        public void SetRawTF(int selectedDoc, String word) {
            this.value = RetRawTF(selectedDoc, word);
        }

        public void SetRegTF(int selectedDoc, String word) {
            List<String[]> list = counter.getAllDocsSplit();
            String[] array = list.get(selectedDoc);
            double value = array.length;

            this.value = RetRawTF(selectedDoc, word)/value;
        }

        public void SetLogNormTF(int selectedDoc, String word) {
            this.value = Math.log(RetRawTF(selectedDoc, word) + 1);
        }

        public void SetDoubleNormHalfValTF(int selectedDoc, String word) {
            List<Map<String, Integer>> list = counter.getAllDocsWC();
            Map<String, Integer> map = list.get(selectedDoc);
            double maxOccurTerm = Collections.max(map.values());

            this.value = 0.5 + (0.5 * (RetRawTF(selectedDoc, word)/maxOccurTerm));
        }

        public void SetDoubleNormKValTF(int selectedDoc, String word, double k) {
            List<Map<String, Integer>> list = counter.getAllDocsWC();
            Map<String, Integer> map = list.get(selectedDoc);
            double maxOccurTerm = Collections.max(map.values());

            this.value = k + (k * (RetRawTF(selectedDoc, word)/maxOccurTerm));
        }
    }

}
