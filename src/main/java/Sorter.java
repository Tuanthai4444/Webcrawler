import java.util.ArrayList;
import java.util.List;

public class Sorter {

    private final List<String> texts;

    public Sorter(List<String> texts) {
        this.texts = texts;
        WordCounter counter = new WordCounter(texts);
        TFIDFCalculator tfidf = new TFIDFCalculator(counter);
        TFIDFCalculator.IDF idf = tfidf.new IDF();
    }
}