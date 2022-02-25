package day09;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Day 9 "Smoke Basin" part 1.
 */
public class Puzzle1 {

    int size = 100;
    
    int[][] map = new int[size + 2][size + 2];

    /**
     * Reads input file into 2D array.
     */
    void readInput(String file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            for (int i = 1; i <= size; i++) {
                String line = reader.readLine(); 
                for (int j = 1; j <= size; j++) {
                    map[i][j] = line.charAt(j - 1) - '0';
                }
                
                map[0][i] = 9;        // Top border
                map[size + 1][i] = 9; // Bottom border
                map[i][0] = 9;        // Left border
                map[i][size + 1] = 9; // Right border
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Checks is given point is a low.
     */
    boolean isLow(int i, int j) {
        int depth = map[i][j];
        
        if (map[i - 1][j] <= depth) return false;
        if (map[i + 1][j] <= depth) return false;
        if (map[i][j - 1] <= depth) return false;
        if (map[i][j + 1] <= depth) return false;

        return true;
    }
    
    /**
     * Solves the puzzle.
     */
    void process() {
        int count = 0;

        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                if (isLow(i, j)) {
                    count = count + map[i][j] + 1;
                }
            }
        }

        System.out.println("Risk level: " + count);
    }
    
    public static void main(String[] args) {
        Puzzle1 p = new Puzzle1();
        p.readInput(args[0]);
        p.process();
    }
    
}
