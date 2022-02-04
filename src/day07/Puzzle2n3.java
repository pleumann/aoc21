package day07;

/**
 *  Treachery of the Whales part 2, naive solution, O(n^3).
 */
public class Puzzle2n3 {
    
    void process(String file) {
        int[] values = Util.readSingleLineIntArray(file);           // O(n)
        
        int length = values.length;
        
        int bestPos = 0;
        int bestFuel = Integer.MAX_VALUE;
        
        for (int i = 0; i <= length; i++) {                         // O(n^3)
            int fuel = 0;
            
            for (int j: values) {                                   // O(n^2)
                int d = Math.abs(j - i);
                for (int k = 1; k <= d; k++) {                      // O(n)
                    fuel = fuel + k;
                }
            }
            
            if (fuel < bestFuel) {
                bestFuel = fuel;
                bestPos = i;
            }
        }
        
        System.out.printf("Best position is %d, needs %d fuel.\n", bestPos, bestFuel);
    }
    
    public static void main(String[] args) {
        new Puzzle2n3().process(args[0]);
    }
    
}
