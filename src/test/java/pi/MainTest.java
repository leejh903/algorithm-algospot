package pi;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void charCopy() {
        String M = "aaaaaa";

        char[] copy = new char[M.length()];
        Arrays.fill(copy, M.charAt(0));
        assertArrayEquals(M.toCharArray(), copy);
    }
}