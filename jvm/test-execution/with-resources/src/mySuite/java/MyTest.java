import org.junit.Test;

import java.util.Properties;
import java.io.InputStream;

import static org.junit.Assert.*;

public class MyTest {

    @Test
    public void test() throws Exception {
        Properties properties = new Properties();
        InputStream in = MyTest.class.getResourceAsStream("test.properties");
        assertNotNull("Test resource not found", in);

        properties.load(in);
        String value = properties.getProperty("value");
        in.close();

        assertEquals(value, "42");
    }
}
