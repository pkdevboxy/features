import org.junit.Test;

import java.util.Properties;
import java.io.InputStream;

import static org.junit.Assert.*;

public class MyTest {

    @Test
    public void test() {
        String value;
        try {
           Properties properties = new Properties();
           InputStream in = MyTest.class.getResourceAsStream("test.properties");
           properties.load(in);
           value = properties.getProperty("value");
           in.close();
        } catch (Throwable e) {
            throw new RuntimeException("Test resource not found", e);
        }
        assertEquals(value, "42");
    }
}
