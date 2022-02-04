package day07;

/**
 *  Treachery of the Whales part 2, O(n).
 */
public class Puzzle2 {
    
    void process(String file) {
        int[] values = Util.readSingleLineIntArray(file);           // O(n)
        
        int length = values.length;
        
        int sum = 0;
        for (int i: values) {                                       // O(n)
            sum += i;
        }

        int average = sum / length;
        
        int bestPos = 0;
        int bestFuel = Integer.MAX_VALUE;
        
        for (int i = average; i <= average + 1; i++) {              // O(n)
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
        new Puzzle2().process(args[0]);
    }
    
}
