package day01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Day 1 "Sonar Sweep" part 1.
 */
public class Puzzle1 {
    
    int[] input;

    void loadInput(String file) {
        try {
            ArrayList<String> list = new ArrayList();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            reader.lines().forEach(list::add);

            input = new int[list.size()];

            for (int i = 0; i < list.size(); i++) {
                input[i] = Integer.parseInt(list.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    void checkReport() {
        int count = 0;
        
        for (int i = 1; i < input.length; i++) {
            if (input[i] > input[i - 1]) {
                count++;
            }
        }
        
        System.out.println(count);
    }

    public static void main(String[] args) {
        Puzzle1 p = new Puzzle1();
        p.loadInput(args[0]);
        p.checkReport();
    }

}
