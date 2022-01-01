package day06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Day 6 "Lanternfish" part 1.
 */
public class Puzzle1 {
    
    void process(String file, int numDays) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
         
            String[] a = reader.readLine().split(",");
            
            long[] fish = new long[9];
            long count = 0;
            
            for (String s: a) {
                fish[Integer.parseInt(s)]++;
                count++;
            }
            
            for (int days = 1; days <= numDays; days++) {
                long temp = fish[0];
                
                for (int i = 0; i < 8; i++) {
                    fish[i] = fish[i + 1];
                }

                fish[8] = temp;
                fish[6] += temp;
                count += temp;

                System.out.println("On day " + days + " there are " + count + " lanternfish");
            }
            
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        
    }
    
    public static void main(String[] args) {
        new Puzzle1().process(args[0], 80);
    }
    
}
