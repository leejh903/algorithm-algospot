package quadtree;

import org.junit.Test;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void appendEmptyString() {
        char a = 'a';
        String str = a + "";
        assertEquals(str, "a");
        assertEquals(String.valueOf(a), "a");
    }
}