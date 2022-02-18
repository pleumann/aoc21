package day08;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *  Seven Segment Search part 1.
 */
class Puzzle1 {

    int count;

    void processLine(String line) {
        String[] a = line.split("\\s\\|\\s"); // Separate left/right part
        String[] b = a[1].trim().split(" ");  // Separate the four digits

        for (String s: b) {
            System.out.println(s);
            int l = s.length();
            if (l == 2 || l == 3 || l == 4 || l == 7) {
                count++;
            }
        }
        
        System.out.println();
    }

    void process(String file) {
        try {
            new BufferedReader(new FileReader(file)).lines().forEach(this::processLine);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        System.out.println("Result=" + count);
    }
    
    public static void main(String[] args) {
        new Puzzle1().process(args[0]);
    }
    
}
