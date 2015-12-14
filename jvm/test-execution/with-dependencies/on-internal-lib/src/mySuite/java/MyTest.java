import org.junit.Test;

import static org.junit.Assert.assertThat;
import static my.testing.Utils.*;
import static my.testing.Lib.*;

public class MyTest {

    @Test
    public void test() {
        assertThat(FORTY_TWO, isTheAnswerToTheUltimateQuestionOfLifeTheUniverseAndEverything());
    }
}
