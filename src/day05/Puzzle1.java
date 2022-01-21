package day05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Day 5 "Hydrothermal Venture" part 1. Horizontal and vertical lines only.
 */
public class Puzzle1 {
    
    int[][] map = new int[1000][1000];

    int count;
    
    void process(String file) {
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            
            String s = reader.readLine();
            while (s != null) {
                System.out.printf("%-20s", s);
                
                String[] t = s.split(",|\\s->\\s");
                
                int x1 = Integer.parseInt(t[0]);
                int y1 = Integer.parseInt(t[1]);
                int x2 = Integer.parseInt(t[2]);
                int y2 = Integer.parseInt(t[3]);
                
                drawLine(x1, y1, x2, y2);
                
                s = reader.readLine();
            }
            
            System.out.println("Count: " + count);
            
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    void drawLine(int x1, int y1, int x2, int y2) {
        if (x1 == x2) {
            int start = Math.min(y1, y2);
            int end = Math.max(y1, y2);

            System.out.printf("(vertical, start=%d, end=%d)\n", start, end);
            
            for (int i = start; i <= end; i++) {
                if (map[x1][i] == 1) {
                    count++;
                }
                map[x1][i]++;
            }
        } else if (y1 == y2) {
            int start = Math.min(x1, x2);
            int end = Math.max(x1, x2);

            System.out.printf("(horizontal, start=%d, end=%d)\n", start, end);
            
            for (int i = start; i <= end; i++) {
                if (map[i][y1] == 1) {
                    count++;
                }
                map[i][y1]++;
            }
        } else {
            System.out.println("(diagonal, not supported yet)");
        }
    }
    
    public static void main(String[] args) {
        new Puzzle1().process(args[0]);
    }
    
}
