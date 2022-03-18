package day12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Passage pathing part 1.
 */
public class Puzzle1 {

    /**
     * Helper class representing a cave (=node) and its neighbors (=edges).
     */
    class Cave {

        /**
         * The name of our cave.
         */
        String name;
        
        /**
         * The other paths that are reachable from this one.
         */
        ArrayList<Cave> exits = new ArrayList<>();
        
        /**
         * How many time this cave may be visited.
         */
        int visits;
        
        /**
         * Creates a cave for the given name.
         */
        public Cave(String name) {
            this.name = name;
            visits = name.charAt(0) >= 'a' ? 1 : Integer.MAX_VALUE; 
        }
        
    }
    
    /**
     * A map of names to corresponding caves, for quick access.
     */
    HashMap<String, Cave> cavesByName = new HashMap<>();
    
    /**
     * The start cave.
     */
    Cave start;
    
    /**
     * The end cave.
     */
    Cave end;
    
    /**
     * Gets a cave with the given name, creating it if needed.
     */
    Cave getCave(String s) {
        Cave c = cavesByName.get(s);
        
        if (c == null) {
            c = new Cave(s);
            cavesByName.put(s, c);
        }
        
        return c;
    }

    /**
     * Creates the puzzle, loads data from the given file.
     */
    Puzzle1(String file) {
        System.out.print("Loading ");
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String s = reader.readLine();
            while (s != null) {
                System.out.print('.');
                
                int p = s.indexOf('-');
                Cave from = getCave(s.substring(0, p));
                Cave to = getCave(s.substring(p + 1));
                
                from.exits.add(to);
                to.exits.add(from);
                
                s = reader.readLine();
            }
            
            start = getCave("start");
            end = getCave("end");
            
            System.out.println();
            System.out.println();
            System.out.println("There are " + cavesByName.size() + " caves in total.");
            for (Cave c: cavesByName.values()) {
                System.out.println("Cave " + c.name + " has " + c.exits.size() + " exits.");
            }
            System.out.println();
            
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        
    }
    
    /**
     * Implements path finding via recursive backtracking. Finds all possible
     * paths from the given cave to the end cave and returns their number.
     * Boolean parameter reflects whether we are in possession of an "extra
     * life" for a second visit to a small cave. Note we are not actually
     * interested in the paths themselves, just their number, so we don't
     * keep track of the actual path we've traveled so far (although it's
     * implicit in the recursive calls).
     */
    int recurse(Cave cave, boolean extra) {
        if (cave.visits <= 0) {
            if (extra && cave != start) {
                extra = false;
            } else {
                return 0;
            }
        }
        
        if (cave == end) {
            return 1;
        }

        cave.visits--;
        
        int result = 0;
        for (Cave c: cave.exits) {
            result = result + recurse(c, extra);
        }
        
        cave.visits++;
        
        return result;
    }
    
    /**
     * Kickstarts the pathfinding from the start node with no extra life.
     */
    int recurse() {
        return recurse(start, false);
    }
    
    public static void main(String[] args) {
        Puzzle1 p = new Puzzle1(args[0]);
        System.out.println("There are " + p.recurse() + " paths through the caves.");
    }
    
}
