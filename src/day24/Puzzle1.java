package day24;

import java.util.HashSet;

/**
 * Day 24 "Arithmetic Logic Unit" part 1.
 * 
 * Based on two interesting observsations about the MONAD program:
 * 
 * 1) The program is actually 14 separate little programs communicating only
 *    through the "z" variable. All other values get lost.
 * 
 * 2) Same "z" output value in processing stage n will lead to same results from
 *    thereon (for all combinations of inputs n+1..13). So we can use caching to
 *    avoid duplicate work.
 * 
 * The solution doesn't rely on a VM for executing the MONAD program, but uses
 * compiled-to-Java code instead. This was originally a relic of trying to
 * optimize an earlier brute-force solution, but it also became quite convenient
 * because it can run the program not only as a whole, but piecemeal (i.e. just
 * one of the 14 processing steps). The compiled code expects a "z" value going
 * into that stage and an input to process, and it returns the "z" output value.
 * This allows us to use an rather elegant recursive overall approach.
 */
public class Puzzle1 {

    /**
     * Cache for "z" output values in each of the 14 stages of processing. If
     * a value is in this cache we've tried that path before. It didn't lead to
     * a result (or the program would have ended), so we don't need to try
     * again.
     */
    HashSet<Integer>[] seen = new HashSet[14];

    public Puzzle1() {
        for (int i = 0; i < seen.length; i++) {
            seen[i] = new HashSet<>();
        }
    }

    /**
     * Recursively processes the whole solution space from largest to smallest.
     * Uses the cached "z" values to know when it's not worth recursing (again).
     */
    void process(int index, int z, String s) {
        if (index == 14) {
            if (z == 0) {
                System.out.println("Bingo! Maximum valid model number is: " + s);
                System.exit(0);
            }
            
            return;
        }
        
        for (int i = 9; i >= 1; i--) {
            int zz = Monad.run(index, z, i);
            if (!seen[index].contains(zz)) {
                seen[index].add(zz);
                
                process(index + 1, zz, s + i);
            }
        }
    }
    
    public static void main(String[] args) {
        new Puzzle1().process(0, 0, "");
    }
    
}
