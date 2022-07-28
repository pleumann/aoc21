package day20;

import java.io.IOException;

/**
 * Day 20 "Trench Map" part 2.
 */
public class Puzzle2 {
    
    /**
     * Entry point.
     */
    public static void main(String[] args) {
        try {        
            new Puzzle1().process(args[0], 50);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
