package day09;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Day 9 "Smoke Basin" part 2.
 */
public class Puzzle2 extends Puzzle1 {

    /**
     * Performs a recursive flood fill starting from the given point.
     */
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
    
    @Override
    void process() {
        ArrayList<Integer> basins = new ArrayList<>();

        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                if (isLow(i, j)) {
                    basins.add(floodFill(i, j));
                }
            }
        }

        Collections.sort(basins, Collections.reverseOrder());
        
        System.out.println("Basin sizes: " + basins.get(0) * basins.get(1) * basins.get(2));
    }
    
    public static void main(String[] args) {
        Puzzle2 p = new Puzzle2();
        p.readInput(args[0]);
        p.process();
    }
    
}
