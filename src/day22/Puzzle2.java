package day22;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Reactor Reboot part 2.
 */
public class Puzzle2 {

    /**
     * Helper class representing a block of cubes (that is, again, a cube, but
     * we use a different name to avoid confusion.
     */
    class Block {

        /**
         * Low x/y/z value.
         */
        int[] min = new int[3];

        /**
         * High x/y/z value. Note: In contrast to the input this is exclusive!
         */
        int[] max = new int[3];

        /**
         * Default constructor.
         */
        Block() {
            // Intentionally left blank.
        }
        
        /**
         * Constructor for a block with given coordinate ranges.
         */
        Block(int x1, int x2, int y1, int y2, int z1, int z2) {
            min[0] = x1; max[0] = x2;
            min[1] = y1; max[1] = y2;
            min[2] = z1; max[2] = z2;
        }
        
        /**
         * Calculates the intersection of this block with a given other one.
         * Returns null if the blocks do not intersect.
         */
        Block intersect(Block other) {
            Block result = new Block();
            for (int i = 0; i < 3; i++) {
                if (max[i] <= other.min[i] || min[i] >= other.max[i]) {
                    return null;
                }
                
                result.min[i] = Math.max(min[i], other.min[i]);
                result.max[i] = Math.min(max[i], other.max[i]);
            }
            
            return result;
        }

        /**
         * Subtracts another block from this one. Returns the list of pieces
         * that remain. The pieces are overlap-free.
         */
        ArrayList<Block> subtract(Block other) {
            ArrayList<Block> pieces = new ArrayList();
            
            // Fast track.
            if (intersect(other) == null) {
                pieces.add(this);
                return pieces;
            }
            
            Block b;
            
            b = intersect(new Block(min[0], max[0], min[1], max[1], other.max[2], Integer.MAX_VALUE));
            if (b != null) {
                pieces.add(b);
            }
            b = intersect(new Block(min[0], max[0], min[1], max[1], Integer.MIN_VALUE, other.min[2]));
            if (b != null) {
                pieces.add(b);
            }
            b = intersect(new Block(Integer.MIN_VALUE, other.min[0], Integer.MIN_VALUE, Integer.MAX_VALUE, other.min[2], other.max[2]));
            if (b != null) {
                pieces.add(b);
            }
            b = intersect(new Block(other.max[0], Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE, other.min[2], other.max[2]));
            if (b != null) {
                pieces.add(b);
            }
            b = intersect(new Block(other.min[0], other.max[0], Integer.MIN_VALUE, other.min[1], other.min[2], other.max[2]));
            if (b != null) {
                pieces.add(b);
            }
            b = intersect(new Block(other.min[0], other.max[0], other.max[1], Integer.MAX_VALUE, other.min[2], other.max[2]));
            if (b != null) {
                pieces.add(b);
            }
            
            return pieces;
        }
        
        /**
         * Returns the volume of this block, in cubes.
         */
        long volume() {
            long result = 1;
            
            for (int i = 0; i < 3; i++) {
                result = result * Math.max(max[i] - min[i], 0);
            }
            
            return result;
        }
        
    }
    
    /**
     * The current list of blocks. It has the important invariant that there
     * are no overlaps between any two blocks.
     */
    ArrayList<Block> blocks = new ArrayList<>();

    /**
     * Processes a single line of input coming from our file. First, the new
     * block is subtracted from all existing blocks. This ensures that our list
     * of blocks remains overlap-free. Then, in case of an "on" block, this
     * block is appended to the list of blocks.
     */
     void process(String s) {
        System.out.println(s);
        
        String[] a = s.split("\\s|=|\\.\\.|,");

        boolean on = "on".equals(a[0]);
        
        Block nkotb = new Block(
                Integer.parseInt(a[2]), Integer.parseInt(a[3]) + 1,
                Integer.parseInt(a[5]), Integer.parseInt(a[6]) + 1,
                Integer.parseInt(a[8]), Integer.parseInt(a[9]) + 1);

        ArrayList<Block> pieces = new ArrayList<>();

        for (Block b: blocks) {
            pieces.addAll(b.subtract(nkotb));
        }

        if (on) {
            pieces.add(nkotb);
        }
        
        blocks = pieces;
        
    }
    
    /**
     * Entry point.
     */
    public static void main(String[] args) {
        try {
            Puzzle2 p = new Puzzle2();
            new BufferedReader(new FileReader(args[0])).lines().forEach(p::process);
            
            int count = p.blocks.size();
            long total = 0;
            for (int i = 0; i < count; i++) {
                total += p.blocks.get(i).volume();
            }
            
            System.out.println();
            System.out.println("Total cubes: " + total + " (in " + count + " blocks)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
