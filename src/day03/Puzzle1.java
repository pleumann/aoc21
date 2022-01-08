package day03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Day 3 "Binary Diagnostic" part 1
 */
public class Puzzle1 {
    
    /**
     * Holds the input lines.
     */
    ArrayList<String> values = new ArrayList<>();

    /**
     * Holds the width of our input words because we need it frequently.
     */
    int numBits;

    /**
     * Initializes a puzzle oject from the given file.
     */
    Puzzle1(String file) {
        try {
            new BufferedReader(new FileReader(file)).lines().forEach(values::add);
            numBits = values.get(0).length();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    /**
     * Checks if the '1' bit is dominant in the given list of input words at the
     * given column.
     */
    boolean isOneDominant(ArrayList<String> list, int place) {
        int count = (int)list.stream().filter(s -> s.charAt(place) == '1').count();
        
        return count >= list.size() - count;
    }

    /**
     * Calculates epsilon and gamma, then power demand.
     */
    int getPowerDemand() {
        int epsilon = 0;
        int gamma = 0;
        
        for (int i = 0; i < numBits; i++) {
            epsilon = epsilon << 1;
            gamma = gamma << 1;
                
            if (isOneDominant(values, i)) {
                epsilon = epsilon | 1;
            } else {
                gamma = gamma | 1;
            }
        }
        
        return epsilon * gamma;
    }
    
    public static void main(String[] args) {
        Puzzle1 p = new Puzzle1(args[0]);
        System.out.println(p.getPowerDemand());
    }

}
