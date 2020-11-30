import java.io.IOException;
import java.util.*;

public class ParseImportantWordsTest {

    public static void main(String[] args) throws IOException {
        final int SELECTED_DOC = 0;
        final int MAX_LINKS = 50;
        final double K_VAL = 0.4;
        //Test the crawler for getting nested links
        //Should print out n-1 other links along with passed link for total of n
        Crawler crawler = new Crawler(MAX_LINKS);
        crawler.startCrawl("https://en.wikipedia.org/wiki/Hippocampus");
        List<String> listOfLinks = crawler.getLinkList();
        for(String url : listOfLinks) {
            System.out.println(url);
        }

        System.out.println();

        //Test the parser to get link text
        //Should print out size (in characters) of text
        //Should also print out nth text body
        Parser parser = new Parser(listOfLinks);
        List<String> parseList = parser.parseText();
        for(String s : parseList) {
            System.out.println(s.length());
        }
        System.out.println(parseList.get(SELECTED_DOC));

        System.out.println();

        //Test the word counter for correct word to word count mapping for nth document
        //Should print out array string representation of split nth document
        //Should print out map representation of each word to word count SORTED
        //then finally the set size
        WordCounter counter = new WordCounter(parseList);
        String[] split = counter.getAllDocsSplit().get(SELECTED_DOC);
        Map<String, Integer> nthDocument = counter.allDocsWC.get(SELECTED_DOC);

        List<Map.Entry<String, Integer>> list1 = new ArrayList<>(nthDocument.entrySet());
        list1.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        Map<String, Integer> sortedDocument = new LinkedHashMap<>();
        for(Map.Entry<String, Integer> e : list1) {
            sortedDocument.put(e.getKey(), e.getValue());
        }

        System.out.println(Arrays.toString(split));
        System.out.println(sortedDocument);
        System.out.println(sortedDocument.size());

        System.out.println();

        //Tests the TFIDF for correct word to TF*IDF mapping for nth document with K val
        //Should print out map representation of word to TF*IDF value and map size SORTED
        //then finally set size
        Map<String, Double> result = new TreeMap<>();
        Map<String, Integer> selectedDocWC = counter.getAllDocsWC().get(SELECTED_DOC);
        Map<String, Double> idfCheck = new TreeMap<>();
        Map<String, Double> tfCheck = new TreeMap<>();

        for(String word : selectedDocWC.keySet()) {
            TFIDF tfIdf = new TFIDF(counter);

            TFIDF.IDF idf = new TFIDF.IDF(counter);
            idf.SetRegIDF(word);

            TFIDF.TF tf = new TFIDF.TF(counter);
            tf.SetDoubleNormKValTF(SELECTED_DOC, word, K_VAL);

            tfIdf.setIdf(idf);
            tfIdf.setTf(tf);

            double wordVal = tfIdf.getTFIDF();
            result.put(word, wordVal);

            idfCheck.put(word, tfIdf.getIdf().getValue());
            tfCheck.put(word, tfIdf.getTf().getValue());
        }

        List<Map.Entry<String, Double>> list2 = new ArrayList<>(result.entrySet());
        list2.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        Map<String, Double> sortedResult = new LinkedHashMap<>();
        for(Map.Entry<String, Double> e : list2) {
            sortedResult.put(e.getKey(), e.getValue());
        }


        System.out.println(sortedResult);
        System.out.println(sortedResult.size());

        System.out.println();

        //Test the idf and tf for correctness
        System.out.println(idfCheck);
        System.out.println(tfCheck);
    }



    public static class valueComparator implements Comparator<String> {
        TreeMap<String, Double> map = new TreeMap<>();

        public valueComparator(Map<String, Double> map) {
            this.map.putAll(map);
        }

        @Override
        public int compare(String s1, String s2) {
            return Double.compare(map.get(s2), map.get(s1));
        }
    }
}