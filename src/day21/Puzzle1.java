package day21;

/**
 * "Dirac Dice" part 1.
 */
public class Puzzle1 {

    /**
     * The current positions of both players (-1 for easy zero-base and modulo).
     */
    static int[] positions = { 2, 6 }; // 3, 7 for example
    
    /**
     * The current scores of both players.
     */
    static int[] scores = { 0, 0 };

    /**
     * The last roll of our determinictic dice.
     */
    static int dice;
    
    /**
     * The number of moves simulated to far.
     */
    static int count;

    /**
     * Rolls the dice, returns the value.
     */
    static int roll() {
        System.out.print('.');
        
        count++;
        dice++;
        if (dice > 100) {
            dice = 1;
        }
        
        return dice;
    }

    /**
     * Simulates the whole game until either player has >= 1000 points.
     */
    static void simulate() {
        int player = 0;
        
        while (true) {
            int points = roll() + roll() + roll();
            
            positions[player] = (positions[player] + points) % 10;
            scores[player] = scores[player] + positions[player] + 1;
            
            if (scores[player] >= 1000) {
                break;
            }
            
            player = 1 - player;
        }
    }   

    /**
     * Entry point. No parameters used.
     */
    public static void main(String[] args) {
        System.out.println();
        System.out.println("*** AoC 2022.21 'Dirac Dice' part 1 ***");
        System.out.println();
        
        simulate();
        
        System.out.println();
        System.out.println();
        
        for (int i = 0; i < 2; i++) {
            System.out.println(String.format("Player %d has %d points @ %d rolls, product is %d", 
                    i, scores[i], count, scores[i] * count));
        }
        System.out.println();
    }
    
}
