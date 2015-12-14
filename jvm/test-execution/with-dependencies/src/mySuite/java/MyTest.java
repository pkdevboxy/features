import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class MyTest {

    @Test
    public void test() {
        assertThat(2 + 2, is(4));
    }
}
