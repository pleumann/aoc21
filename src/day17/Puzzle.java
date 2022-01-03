package day17;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Day 17 "Trick Shot" parts 1 and 2.
 */
public class Puzzle {

    int x1, x2, y1, y2, max, best, xmin, xmax, ymin, ymax;
    
    Puzzle(String s) {
        String a[] = s.split("[^0-9\\-]+");
        x1 = Integer.parseInt(a[1]);
        x2 = Integer.parseInt(a[2]);
        y1 = Integer.parseInt(a[3]);
        y2 = Integer.parseInt(a[4]);
    }
    
    boolean shoot(int dx, int dy) {
        int x = 0;
        int y = 0;
        int step = 0;
        
        max = Integer.MIN_VALUE;
        
        while (true) {
            step = step + 1;
            
            x = x + dx;
            y = y + dy;
            
            max = Math.max(y, max);
            
            dx = dx - Integer.compare(dx, 0);
            dy = dy - 1;
            
            if (dy < 0 && y < y1) {
                if (x < x1) {
                    System.out.print('<');
                    return false;
                } else if (x > x2) {
                    System.out.print('>');
                    return false;
                } else {
                    System.out.print('v');
                    return false;
                }
            }
            
            if (x1 <= x && x <= x2 && y1 <= y && y <= y2) {
                return true;
            }
        }
    }
    
    void process() {
        best = Integer.MIN_VALUE;
        
        xmin = Integer.MAX_VALUE;
        ymin = Integer.MAX_VALUE;
        
        xmax = Integer.MIN_VALUE;
        ymax = Integer.MIN_VALUE;
        
        int hits = 0;
        int misses = 0;
        
        for (int i = 1; i <= x2; i++) {
            for (int j = y1; j <= Math.abs(y1); j++) {
                if (shoot(i, j)) {
                    System.out.print('X');
                    
                    hits++;
                    best = Math.max(best, max);
                    
                    xmin = Math.min(xmin, i);
                    xmax = Math.max(xmax, i);
                    ymin = Math.min(ymin, j);
                    ymax = Math.max(ymax, j);
                    
                } else {
                    misses++;
                }
            }
            System.out.println();
        }
        
        System.out.println();
        System.out.println("Maximum height: " + best);
        System.out.println("Number of hits: " + hits);
    }
    
    public static void main(String[] args) {
        try {        
            String s = new BufferedReader(new FileReader(args[0])).readLine();
            new Puzzle(s).process();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
