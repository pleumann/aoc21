package day11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Dimbo Ocotopus parts 1 and 2.
 */
public class Puzzle {

    int[][] values = new int[10][10];

    int flashes;
    
    int round;
    
    void dump() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(values[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
         
    void cycle(int i, int j) {
        if (i < 0 || i > 9 || j < 0 || j > 9) {
            return;
        }
        
        if (values[i][j] < 10) {
            values[i][j]++;
            if (values[i][j] > 9) {
                round++;
                flashes++;
                cycle(i - 1, j - 1);
                cycle(i - 1, j);
                cycle(i - 1, j + 1);
                cycle(i, j - 1);
                cycle(i, j + 1);
                cycle(i + 1, j - 1);
                cycle(i + 1, j);
                cycle(i + 1, j + 1);
            }
        }
        
    }
    
    void process(String file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            for (int i = 0; i < 10; i++) {
                String line = reader.readLine(); 
                for (int j = 0; j < 10; j++) {
                    values[i][j] = line.charAt(j) - '0';
                }
            }
            
            dump();
            
            for (int days = 1; days <= 100000; days++) {
                round = 0;
                
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        cycle(i, j);
                    }
                }
                
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if (values[i][j] == 10) {
                            values[i][j] = 0;
                        }
                    }
                }
                
                System.out.println("After " + days + "steps there have been " + flashes + " flashes");
                dump();
                if (round == 100) {
                    System.out.println("ALL FLASHED !!!");
                    System.exit(1);
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        
    }
    
    public static void main(String[] args) {
        new Puzzle().process(args[0]);
    }
    
}
