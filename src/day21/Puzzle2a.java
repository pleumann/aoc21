package day21;

/**
 * "Dirac Dice" part 2. Performs an exhaustive depth-first recursive traversal
 * of the possible game states. Since three three-sided dice can only yield 7
 * different results we don't have to follow each path individually. Instead we
 * follow each "sum" and pass a "frequency" factor.
 */
public class Puzzle2a {
 
    /**
     * The frequencies of sums of three three-sided dice.
     */
    static final int[] frequencies = { 0, 0, 0, 1, 3, 6, 7, 6, 3, 1 };

    /**
     * The current positions of both players (-1 for easy zero-base and modulo).
     */
    static int[] positions = { 2, 6 }; // 3, 7 for example

    /**
     * The current scores of both players.
     */
    static int[] scores = { 0, 0 };

    /**
     * The number of wins of both players. Long needed because of large numbers.
     */
    static long[] wins = { 0, 0 };

    /**
     * The number of rounds simulated so far (reset to zero regularly).
     */
    static int count = 0;
    
    /**
     * Simulates one move, recursively descending into simulate() until either
     * player has >= 21 points.
     */
    static void simulate(int player, int points, long factor) {
        count++;
        if (count == 100000) {
            System.out.print('.');
            count = 0;
        }
        
        int savePosition = positions[player];
        int saveScore = scores[player];

        positions[player] = (positions[player] + points) % 10;
        scores[player] = scores[player] + positions[player] + 1;

        if (scores[player] >= 21) {
            wins[player] = wins[player] + factor;
        } else {
            for (int i = 3; i <= 9; i++) {
                simulate(1 - player, i, factor * frequencies[i]);
            }
        }

        positions[player] = savePosition;
        scores[player] = saveScore;
    }

    /**
     * Entry point. No parameters used.
     */
    public static final void main(String[] args) {
        System.out.println();
        System.out.println("*** AoC 2022.21 'Dirac Dice' part 2 ***");
        System.out.println();

        for (int i = 3; i <= 9; i++) {
            simulate(0, i, frequencies[i]);
        }

        System.out.println();
        System.out.println();
        
        for (int i = 0; i < 2; i++) {
            System.out.println("Player " + i + " wins in " + wins[i] + " universes");
        }
        System.out.println();
    }

}
