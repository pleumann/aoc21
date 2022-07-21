package day19;

import java.util.HashSet;

/**
 * Helper class for dealing with rotation matrices and vectors.
 */
public class Matrix {

    /**
     * The three basic rotation matrices. Only used internally.
     */
    private static final int[][][] ROT_MAT = {
        {
            {  1,  0,  0 },
            {  0,  0, -1 },
            {  0,  1,  0 }
        },
        {
            {  0,  0,  1 },
            {  0,  1,  0 },
            { -1,  0,  0 }
        },
        {
            {  0, -1,  0 },
            {  1,  0,  0 },
            {  0,  0,  1 }
        }
    };

    /**
     * An array of rotation vectors that we are going to populate.
     */
    public static final int[][] ROT_VEC = new int[24][];

    /**
     * Rotates the given point around the given axis.
     */
    private static int[] rotate(int[] point, int axis) {
        int[] result = new int[3];
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int k = ROT_MAT[axis][i][j];
                if (k != 0) {
                    result[i] = result[i] + k * point[j];
                }
            }
        }
        
        return result;
    }

    /**
     * Converts a point to a printable string.
     */
    public static String toString(int[] point) {
        return "{ " + point[0] + ", " + point[1] + ", " + point[2] + " }";
    }

    /**
     * Generates the array of rotation vectors.
     */
    static void generateVectors() {
        System.out.println("Generating rotation vectors...");
        
        HashSet<String> h = new HashSet<>();
        int to = 0;

        // Identity, every component is "itself" (+1, actually, to store sign)
        int[] p = new int[] {1, 2, 3};
        
        // Apply all possible rotations
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    String s = toString(p);
                    
                    // Add only distinct vectors
                    if (!h.contains(s)) {
                        h.add(s);
                        ROT_VEC[to++] = p;
                        System.out.println(toString(p));
                    }
                    
                    p = rotate(p, 2);
                }
                p = rotate(p, 1);
            }
            p = rotate(p, 0);
        }
        
        System.out.println(h.size() + " elements");
        System.out.println();
    }

}
