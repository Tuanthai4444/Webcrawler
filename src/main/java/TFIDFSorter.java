import java.util.List;

public class TFIDFSorter {

    List<String> texts;
    TFIDFCalculator calculator;

    public TFIDFSorter(List<String> texts, TFIDFCalculator calculator) {
        this.texts = texts;
        this.calculator = calculator;
    }


}
