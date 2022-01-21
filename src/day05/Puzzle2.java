package day05;

/**
 * Day 5 "Hydrothermal Venture" part 2. Generalized solution.
 */
public class Puzzle2 extends Puzzle1 {

    @Override
    void drawLine(int x1, int y1, int x2, int y2) {
        int dx = x1 == x2 ? 0 : x1 < x2 ? 1 : -1;
        int dy = y1 == y2 ? 0 : y1 < y2 ? 1 : -1;

        int steps = Math.max(Math.abs(x2 - x1), Math.abs(y2 - y1));

        System.out.printf("(dx=%d, dy=%d, steps=%d)\n", dx, dy, steps);
        
        int i = x1;
        int j = y1;

        for (int k = 0; k <= steps; k++) {
            if (map[i][j] == 1) {
                count++;
            }
            map[i][j]++;
            
            i = i + dx;
            j = j + dy;
        }
    }
    
    public static void main(String[] args) {
        new Puzzle2().process(args[0]);
    }
    
}
