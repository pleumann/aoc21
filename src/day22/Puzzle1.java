package day22;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;

/**
 * Reactor Reboot part 1.
 */
public class Puzzle1 {
    
    /**
     * Low end of interest on each axis.
     */
    static final int MIN = -50;
    
    /**
     * High end of interest on each axis.
     */
    static final int MAX = 50;
    
    /**
     * Hash set containing the currently active cubes.
     */
    HashSet<String> cubes = new HashSet<>();
    
    /**
     * Returns a printable string for a coordinate triplet that can also serve
     * as hash key.
     */
    String key(int x, int y , int z) {
        return "(" + x + "," + y + "," + z + ")";
    }
    
    /**
     * Processes a single line of input coming from the file. Naive solution
     * that walks through all affected cubes and turns them either on or off.
     */
    void process(String s) {
        System.out.println(s);
        
        String[] a = s.split("\\s|=|\\.\\.|,");
       
        boolean b = "on".equals(a[0]);
        
        int x1 = Math.max(Integer.parseInt(a[2]), MIN);
        int x2 = Math.min(Integer.parseInt(a[3]), MAX);

        int y1 = Math.max(Integer.parseInt(a[5]), MIN);
        int y2 = Math.min(Integer.parseInt(a[6]), MAX);

        int z1 = Math.max(Integer.parseInt(a[8]), MIN);
        int z2 = Math.min(Integer.parseInt(a[9]), MAX);
        
        for (int x = x1; x <= x2; x++) {
            for (int y = y1; y <= y2; y++) {
                for (int z = z1; z <= z2; z++) {
                    String k = key(x, y, z);
                    
                    if (b) {
                        cubes.add(k);
                    } else {
                        cubes.remove(k);
                    }
                }
            }
        }
    }

    /**
     * Entry point.
     */
    public static void main(String[] args) {
        try {
            Puzzle1 p = new Puzzle1();
            new BufferedReader(new FileReader(args[0])).lines().forEach(p::process);
            
            System.out.println();
            System.out.println("Total cubes: " + p.cubes.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
