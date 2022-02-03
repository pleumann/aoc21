package day07;

import java.util.Arrays;
        
/**
 *  Treachery of the Whales part 1, O(n^2).
 */
public class Puzzle1 {
    
    void process(String file) {
        int[] values = Util.readSingleLineIntArray(file);           // O(n)
        
        int length = values.length;
            
        Arrays.sort(values);                                        // O(n^2)

        int median = values[length / 2];
        if (length % 2 == 0) {
            median = (median + values[length / 2 - 1]) / 2;
        }

        int fuel = 0;
        for (int i: values) {                                       // O(n)
            fuel += Math.abs(median - i);
        }

        System.out.printf("Best position is %d, needs %d fuel.\n", median, fuel);
    }
    
    public static void main(String[] args) {
        new Puzzle1().process(args[0]);
    }
    
}
