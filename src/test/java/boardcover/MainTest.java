package boardcover;

import org.junit.Test;

import static org.junit.Assert.*;

public class MainTest {
    @Test
    public void blocks() {
        for (Main.Block block : Main.Block.blocks) {
            System.out.println(block.getDirection());
        }
    }
}