package day25;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Day 25 "Sea Cucumber". Not really complicated, but a couple of edge cases
 * need to be done right because cucumber might die or replicate otherwise. It
 * also helps to read the instructions properly (east before south, same step).
 */
public class Puzzle {

    /**
     * Our 2D map. Unlike Game of Life we don't need a second copy.
     */
    char[][] map;
    
    /**
     * Step counter.
     */
    int step = 1;
    
    /**
     * Loads the initial state from a file.
     */
    void init(String file) {
        ArrayList<String> list = new ArrayList<>();
        
        try {
            new BufferedReader(new FileReader(file)).lines().forEach(list::add);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            System.exit(0);
        }
        
        map = new char[list.size()][];
        
        for (int i = 0; i < list.size(); i++) {
            map[i] = list.get(i).toCharArray();
        }
        
    }
    
    /**
     * Prints the current state to the console.
     */
    void dump() {
        System.out.println("--- After " + step + " steps ---");
        for (char[] c : map) {
            System.out.println(new String(c));
        }
        System.out.println();
    }
    
    /**
     * Moves the given character from the source position to the target position
     * if the latter is free ('.'). Returns true if movement was possible. Note
     * there is no checking of the source. This happens one layer up, so we can
     * use this method for both east and south movement.
     */
    boolean move(char c, int x1, int y1, int x2, int y2) {
        if (map[x1][y1] == c) {
            if (map[x2][y2] == '.') {
                map[x2][y2] = c;
                map[x1][y1] = '.';
                return true;
            }
        }
        
        return false;
    }

    /**
     * Tries to move all '>' sea cucumber to the east. Returns true if at least
     * one movement was made.
     */
    boolean east() {
        boolean changed = false;
        
        for (int row = 0; row < map.length; row++) {  
            char first = map[row][0];                   // Store old first char
            int col = 0;
            while (col < map[row].length - 1) {       
                if (move('>', row, col, row, col + 1)) {
                    col++;                              // Skip next after move
                    changed = true;
                }
                col++;
            }
            
            // Edge case: Movement from last column to first column.
            if (col == map[row].length - 1 && first == '.') {
                if (move('>', row, map[row].length - 1, row, 0)) {
                    changed = true;
                }
            }
        }
        
        return changed;
    }

    /**
     * Tries to move all 'v' sea cucumber to the south. Returns true if at least
     * one movement was made.
     */
    boolean south() {
        boolean changed = false;
        
        for (int col = 0; col < map[0].length; col++) {
            char first = map[0][col];                   // Store old first char
            int row = 0;
            while (row < map.length - 1) {
                if (move('v', row, col, row + 1, col)) {
                    row++;                              // Skip next after move
                    changed = true;
                }
                row++;
            }
            
            // Edge case: Move from last row to first row.
            if (row == map.length - 1 && first == '.') {
                if (move('v', map.length - 1, col, 0, col)) {
                    changed = true;
                }
            }
        }
        
        return changed;
    }
    
    /**
     * Simulates as long as there is movement.
     */
    void play() {
        dump();

        boolean e = east();
        boolean s = south();
        
        // Avoid 'east() || south()' here because of short circuit evaluation.
        while (e || s) {
            step++;
            dump();
            
            e = east();
            s = south();
        }
        
        System.out.println(step);
    }
    
    public static void main(String[] args) {
        Puzzle p = new Puzzle();
        p.init(args[0]);
        p.play();
    }
}
