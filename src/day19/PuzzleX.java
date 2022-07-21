package day19;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Day 19 "Beacon Scanner" parts 1 and 2. Parallel variant.
 */
public class PuzzleX extends Puzzle {

    /**
     * Represents a single matching task to be performed.
     */
    class MyTask implements Callable<Boolean> {
        Scanner us, them;
        
        MyTask(Scanner us, Scanner them) {
            this.us = us;
            this.them = them;
        }
        
        public Boolean call() throws Exception {
            return matches(us, them);
        }
    }

    /**
     * Executes the matching tasks. Increase argument for more cores.
     */
    ExecutorService executor = Executors.newFixedThreadPool(8);
   
    /**
     * Creates a puzzle.
     */
    PuzzleX() {
        super();
    }

    /**
     * Finds a match for a scanner within a set of other probes. This basically
     * brute-forces a solution by trying all rotations and all sensible
     * translations. Whenever we have at least 12 overlapping readings we have
     * a match.
     */
    void match(ArrayList<Scanner> found, Scanner us, ArrayList<Scanner> candidates) {
        System.out.println("Searching matches for scanner " + us + " within " + candidates.size() + " candidates");
        System.out.println();

        int old = found.size();
        
        try {
            int count = candidates.size();

            ArrayList<MyTask> tasks = new ArrayList();

            for (Scanner them: candidates) {
                tasks.add(new MyTask(us, them));
            }

            List<Future<Boolean>> futures = executor.invokeAll(tasks);
       
            for (int i = count - 1; i >= 0; i--) {
                if (futures.get(i).get()) {
                    found.add(candidates.get(i));
                    candidates.remove(i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        System.out.println();
        System.out.println();

        if (old != found.size()) {
            for (int i = old; i < found.size(); i++) {
                Scanner them = found.get(i);
                System.out.println("Scanner " + them + " is at " + Matrix.toString(them.translation));
            }                
        } else {
            System.out.println("No luck!");
        }
        
        System.out.println();
    }

    @Override
    void process(String name) throws IOException {
        super.process(name);
        
        executor.shutdownNow();
    }
    
    public static void main(String[] args) {
        try {        
            new PuzzleX().process(args[0]);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}