package day24;

/**
 * Day 24 "Arithmetic Logic Unit" part 2.
 */
public class Puzzle2 extends Puzzle1 {
    
    /**
     * Recursively processes the whole solution space from smallest to largest.
     * Uses the cached "z" values to know when it's not worth recursing (again).
     */
    @Override
    void process(int index, int z, String s) {
        if (index == 14) {
            if (z == 0) {
                System.out.println("Bingo! Minimum valid model number is: " + s);
                System.exit(0);
            }
            
            return;
        }
        
        for (int i = 1; i <= 9; i++) {
            int zz = Monad.run(index, z, i);
            if (!seen[index].contains(zz)) {
                seen[index].add(zz);
                
                process(index + 1, zz, s + i);
            }
        }
    }
    
    public static void main(String[] args) {
        new Puzzle2().process(0, 0, "");
    }
    
}
