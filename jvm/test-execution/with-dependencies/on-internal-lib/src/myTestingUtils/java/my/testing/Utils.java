package my.testing;

import org.hamcrest.Matcher;
import static org.hamcrest.Matchers.equalTo;

public class Utils {

    public static Matcher isTheAnswerToTheUltimateQuestionOfLifeTheUniverseAndEverything() {
        return equalTo(42);
    }
}
