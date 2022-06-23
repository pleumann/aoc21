package day18;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Day 18 "Snailfish" part 2.
 */
public class Puzzle2 {
    
    public static void main(String[] args) {
        try {
            ArrayList<String> a = new ArrayList<>();
            new BufferedReader(new FileReader(args[0])).lines().forEach(s -> a.add(s));
            
            int max = Integer.MIN_VALUE;
            
            // Try all pairs of inputs, nothing to see, please move along.
            for (int i = 0; i < a.size(); i++) {
                for (int j = 0; j < a.size(); j++) {
                    if (i != j) {
                        Puzzle1 p = new Puzzle1();
                        p.process(a.get(i));
                        p.process(a.get(j));
                        int k = p.magnitude();

                        System.out.println("Magnitude: " + k);
                        System.out.println();            
                        
                        max = Math.max(max, k);
                    }
                }
            }

            System.out.println("Highscore: " + max);
            System.out.println();            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
