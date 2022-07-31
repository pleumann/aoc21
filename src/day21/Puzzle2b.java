package day21;

import java.util.HashMap;

/**
 * "Dirac Dice" part 2. Performs a breadth-first non-recursive traversal of the
 * possible game states using sets, collapsing both dice rolls that yield
 * identical sums and game states that have identical positions and scores.
 */
public class Puzzle2b {

    /**
     * Represents a state of our game.
     */
    static class State {
        
        /**
         * The current positions of both players.
         */
        int[] positions = { 0, 0 };
        
        /**
         * The current scores of both players.
         */
        int[] scores = { 0, 0 };

        /**
         * How often does this state exist in this step of the game?
         */
        long factor;

        /**
         * Constructor for two positions, needed for initial state.
         */
        public State(int pos0, int pos1) {
            positions[0] = pos0;
            positions[1] = pos1;
            factor = 1;
        }

        /**
         * Constructor for copying another state.
         */
        public State(State other) {
            for (int i = 0; i < 2 ; i++) {
                positions[i] = other.positions[i];
                scores[i] = other.scores[i];
            }
        }
        
        /**
         * Returns a printable string that we use as a hash key.
         */
        @Override
        public String toString() {
            return "(" + positions[0] + ":" + scores[0] + "," + positions[1] + ":" + scores[1] + ")";
        }
        
    }
    
    /**
     * The frequencies of sums of three three-sided dice.
     */
    static final int[] frequencies = { 0, 0, 0, 1, 3, 6, 7, 6, 3, 1 };

    /**
     * The number of wins of both players. Long needed because of large numbers.
     */
    static long[] wins = { 0, 0 };

    /**
     * The set of states for the current step.
     */
    static HashMap<String, State> states = new HashMap();

    /**
     * Whose turn is it?
     */
    static int player;
    
    /**
     * Simulates all possible moves for all states in the current set, yielding
     * a new set of states for the next step.
     */
    static void simulate() {
        System.out.printf(" %5d | %15d | %15d \n", states.size(), wins[0], wins[1]);
        
        HashMap<String, State> newStates = new HashMap();
        
        for (State s: states.values()) {
            for (int points = 3; points <= 9; points++) {
                int savePosition = s.positions[player];
                int saveScore = s.scores[player];

                s.positions[player] = (s.positions[player] + points) % 10;
                s.scores[player] = s.scores[player] + s.positions[player] + 1;

                if (s.scores[player] >= 21) {
                    wins[player] = wins[player] + s.factor * frequencies[points];
                } else {
                    State t = newStates.get(s.toString());
                    
                    if (t == null) {
                        t = new State(s);                        
                        newStates.put(t.toString(), t);
                    }
                    
                    t.factor += s.factor * frequencies[points];
                }
                
                s.positions[player] = savePosition;
                s.scores[player] = saveScore;
            }
        }
        
        states = newStates;
        player = 1 - player;
    }

    /**
     * Entry point. No parameters used.
     */
    public static final void main(String[] args) {
        System.out.println();
        System.out.println("*** AoC 2022.21 'Dirac Dice' part 2 ***");
        System.out.println();

        System.out.println("States |   Player 0 wins |   Player 1 wins");
        System.out.println("------------------------------------------");
        
        
        State s = new State(2, 6);
        states.put(s.toString(), s);
        
        while (!states.isEmpty()) {
            simulate();
        }

        System.out.println();
        
        for (int i = 0; i < 2; i++) {
            System.out.println("Player " + i + " wins in " + wins[i] + " universes");
        }
        System.out.println();
    }

}
