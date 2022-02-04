package day07;

/**
 *  Treachery of the Whales part 2, original solution, O(n^2).
 */
public class Puzzle2n2 {
    
    void process(String file) {
        int[] values = Util.readSingleLineIntArray(file);           // O(n)
        
        int length = values.length;
        
        int bestPos = 0;
        int bestFuel = Integer.MAX_VALUE;
        
        for (int i = 0; i <= length; i++) {                         // O(n^2)
            int fuel = 0;
            
            for (int j: values) {                                   // O(n)
                int d = Math.abs(j - i);
                fuel += (d * d + d) / 2;                            // Gauss
            }
            
            if (fuel < bestFuel) {
                bestFuel = fuel;
                bestPos = i;
            }
        }
        
        System.out.printf("Best position is %d, needs %d fuel.\n", bestPos, bestFuel);
    }
    
    public static void main(String[] args) {
        new Puzzle2n2().process(args[0]);
    }
    
}
