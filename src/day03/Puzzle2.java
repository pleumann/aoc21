package day03;

import java.util.ArrayList;

/**
 * Day 3 "Binary Diagnostic" part 2
 */
public class Puzzle2 extends Puzzle1 {

    /**
     * Initializes a puzzle oject from the given file.
     */
    public Puzzle2(String file) {
        super(file);
    }

    /**
     * Repeatedly removes lines from the input according to the rules of part 2
     * until only a single word remains. To be called with '1', '0' for Oxygen
     * and '0', '1' for Carbon.
     */
    int filter(char first, char second) {
        ArrayList<String> temp = new ArrayList<>(values);
        
        int bit = 0;
        while (temp.size() != 1) {
            final int j = bit;
            
            char c = isOneDominant(temp, j) ? first : second;
            temp.removeIf(s -> s.charAt(j) != c);
            
            bit++;
        }
        
        return Integer.parseInt(temp.get(0), 2);
    }
        
    /**
     * Calculates oxygen and carbon, then returns life support.
     */
    int getLifeSupport() {
        int oxygen = filter('1', '0');
        int carbon = filter('0', '1');

        return oxygen * carbon;
    }
    
    public static void main(String[] args) {
        Puzzle2 p = new Puzzle2(args[0]);
        System.out.println(p.getLifeSupport());
    }

}
