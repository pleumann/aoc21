package day09;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Day 9 "Smoke Basin" parts 1 and 2.
 */
public class Puzzle {

    int rows = 100;
    
    int columns = 100;
    
    int[][] map = new int[rows + 2][columns + 2];
    
    int floodFill(int row, int column) {
        int count = 0;
        
        if (map[row][column] < 9) {
            map[row][column] = 9;
            count++;

            count += floodFill(row - 1, column);
            count += floodFill(row + 1, column);
            count += floodFill(row, column - 1);
            count += floodFill(row, column + 1);
        }
        
        return count;
    }
    
    void process(String file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            for (int i = 1; i <= rows; i++) {
                String line = reader.readLine(); 
                for (int j = 1; j <= columns; j++) {
                    map[i][j] = line.charAt(j - 1) - '0';
                    
                    map[i][0] = 9;
                    map[i][columns + 1] = 9;
                    
                    map[0][j] = 9;
                    map[rows + 1][j] = 9;
                }
            }
            
            int count = 0;
            
            ArrayList<Integer> basins = new ArrayList<>();
            
            for (int i = 1; i <= rows; i++) {
                for (int j = 1; j <= columns; j++) {
                    int depth = map[i][j];
                    
                    if (map[i-1][j] > depth && map[i+1][j] > depth && map[i][j-1] > depth && map[i][j+1] > depth) {
                        count = count + depth + 1;
                        basins.add(floodFill(i, j));
                    }
                }
            }
            System.out.println("Risk level: " + count);
            
            Collections.sort(basins, Collections.reverseOrder());
            
            System.out.println("Basin sizes: " + basins.get(0) * basins.get(1) * basins.get(2));
            
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        
    }
    
    public static void main(String[] args) {
        new Puzzle().process(args[0]);
    }
    
}
