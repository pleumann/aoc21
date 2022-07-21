package day19;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Day 19 "Beacon Scanner" parts 1 and 2.
 */
public class Puzzle {

    /**
     * Holds the list of all probes.
     */
    ArrayList<Probe> probes = new ArrayList<>();
    
    /**
     * Creates a puzzle.
     */
    Puzzle() {
        Matrix.generateVectors();
    }

    /**
     * Returns the difference vector between two points.
     */
    int[] diff(int[] a, int[] b) {
        int[] c = new int[3];
        for (int i = 0; i < 3; i++) {
            c[i] = b[i] - a[i];
        }
        return c;
    }

    /**
     * Returns the Manhattan distance between two points.
     */
    int manhattan(int[] a, int[] b) {
        int result = 0;
        for (int i = 0; i < 3; i++) {
            result = result + Math.abs(b[i] - a[i]);
        }
        return result;
    }

    /**
     * Finds a match for a probe within a set of other probes. This basically
     * brute-forces a solution by trying all rotations and all sensible
     * translations. Whenever we have at least 12 overlapping points we have
     * a match.
     */
    Probe match(Probe us, ArrayList<Probe> candidates) {
        System.out.println("Searching match for " + us + " within " + candidates.size() + " candidates");
        HashSet<String> ourPoints = us.getAll();

        for (Probe them: candidates) {
            System.out.print("Trying " + them + " ");
            for (int i = 0; i < 24; i++) {
                System.out.write('.');
                them.setRotation(Matrix.ROT_VEC[i]);

                for (int j = 0; j < us.size() - 11; j++) {      // Optimized ???
                    for (int k = j + 1; k < them.size(); k++) { // Optimized ???
                        // If we make any two points match...
                        them.setTranslation(null);
                        them.setTranslation(diff(them.get(k), us.get(j)));

                        // ... and get the rest...
                        HashSet<String> theirPoints = them.getAll();

                        // ... are there at least 12 overlaps?
                        theirPoints.retainAll(ourPoints);
                        if (theirPoints.size() >= 12) {
                            System.out.println();
                            System.out.println("Found a match.");
                            System.out.println();
                            System.out.println(them + " must be at " + Matrix.toString(them.translation));
                            System.out.println();
                            candidates.remove(them);
                            return them;
                        }
                    }
                }
            }
            System.out.println();
        }

        System.out.println("No luck!");
        System.out.println();

        return null;
    }

    /**
     * Solves the puzzle for the given input.
     */
    void process(String file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s = br.readLine();
        
        int number = 0;
        while (s != null) {
            Probe p = new Probe(number);
            System.out.println("Reading " + p + "...");
            number++;
            
            s = br.readLine();
            while (s != null && !"".equals(s)) {
                String[] a = s.split(",");
                int[] b = new int[3];
                for (int i = 0; i < 3; i++) {
                    b[i] = Integer.parseInt(a[i]);
                }
                p.add(b);
                s = br.readLine();
            }
            
            probes.add(p);
            
            if ("".equals(s)) {
                s = br.readLine();
            }
        }
        
        // Use first probe as starting point
        ArrayList<Probe> found = new ArrayList<>();
        found.add(probes.get(0));
        probes.remove(0);

        int i = 0;
        // Try to match until there are no unmatched probes anymore
        while (probes.size() != 0) {
            Probe q = match(found.get(i), probes);
            if (q != null) {
                found.add(q);
            }
            i = (i + 1) % found.size();
        }
        
        // Now that all probes use the same coordinate system, get all beacons
        HashSet<String> total = new HashSet<>();
        for (Probe r : found) {
            total.addAll(r.getAll());
        }
        
        System.out.println("Part 1: There are " + total.size() + " beacons total");
        System.out.println();
        
        int dist = 0;
        
        // Find the maximum distance between two readings
        for (Probe r : found) {
            int[] x = r.translation;
            if (x == null) {
                x = new int[] { 0, 0 ,0 };
            }
            
            for (Probe u : found) {
                int[] y = u.translation;
                if (y == null) {
                    y = new int[] { 0, 0 ,0 };
                }
                
                dist = Math.max(dist, manhattan(x, y));
            }
        }

        System.out.println("Part 2: The ocean size is " + dist +  " Manhattan units");
        System.out.println();
    }
    
    public static void main(String[] args) {
        try {        
            new Puzzle().process(args[0]);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}