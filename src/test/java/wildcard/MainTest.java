package wildcard;

import org.junit.Test;


public class MainTest {

    @Test
    public void subString() {
        String text = "abcd";
        System.out.printf("\"%s\"\n", text.substring(4));
    }
}