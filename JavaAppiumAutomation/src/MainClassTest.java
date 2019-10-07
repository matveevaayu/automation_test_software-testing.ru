import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass {

    @Test
    public void testGetClassString() {
        String helloSubstring = "hello";
        String HelloSubstring = "Hello";
        String testClassString = this.getClassString();

        Assert.assertTrue("getClassString does not contain a substring of 'hello' or 'Hello' " ,testClassString.contains(HelloSubstring) || testClassString.contains(helloSubstring));
    }
}

