package day12;

/**
 * Passage pathing part 2.
 */
public class Puzzle2 extends Puzzle1 {

    /**
     * Creates the puzzle, loads data from the given file.
     */
    public Puzzle2(String file) {
        super(file);
    }
    
    /**
     * Kickstarts the pathfinding from the start node with an extra life.
     */
    @Override
    int recurse() {
        return recurse(start, true);
    }
    
    public static void main(String[] args) {
        Puzzle2 p = new Puzzle2(args[0]);
        System.out.println("There are " + p.recurse() + " paths through the caves.");
    }
    
}
