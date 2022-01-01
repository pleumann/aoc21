package day13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Day 13 "Transparent Origami" part 1.
 */
public class Puzzle1 {

    record Point(int x, int y) { };

    ArrayList<Point> input = new ArrayList<>();
    
    HashSet<Point> output = new HashSet<>();
    
    ArrayList<Point> folds = new ArrayList<>();

    void read(String file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String s = reader.readLine();
            
            while (!"".equals(s)) {
                int p = s.indexOf(',');
                int x = Integer.parseInt(s.substring(0, p));
                int y = Integer.parseInt(s.substring(p + 1));
                
                input.add(new Point(x, y));
                
                s = reader.readLine();
            }
            
            s = reader.readLine();
            while (s != null) {
                int p = s.indexOf('=');
                char axis = s.charAt(p - 1);
                int value = Integer.parseInt(s.substring(p + 1));
                
                if (axis == 'x') {
                    folds.add(new Point(value, Integer.MAX_VALUE));
                } else {
                    folds.add(new Point(Integer.MAX_VALUE, value));
                }
                        
                s = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading input", e);
        }   
    }
        
    void plot(Point p) {
        int x = p.x();
        int y = p.y();
        
        Point t = folds.get(0);
        x = x > t.x() ? 2 * t.x() - x : x;
        y = y > t.y() ? 2 * t.y() - y : y;

        Point q = new Point(x, y);
        output.add(q);
        
        System.out.println(p + " -> " + q);
    }
    
    void dump() {
        System.out.println("Discrete points after 1 fold: " + output.size());
    }
    
    void process(String file) {        
        read(file);
        input.forEach(this::plot);
        System.out.println();
        dump();
    }
    
    public static void main(String[] args) {
        new Puzzle1().process(args[0]);
    }
    
}
