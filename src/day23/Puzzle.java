package day23;

/**
 * Amphipod parts 1 and 2. Recursive depth-first traversal of the solution space
 * with some shortcuts to make it fast. Also, we treat part 1 with the machinery
 * of part 2 by simply putting two rows of "already in place" amphipod
 * underneath.
 */
public class Puzzle {

    /**
     * Hallway columns where an amphipod may rest.
     */
    static final int[] HALLWAY = { 1, 2, 4, 6, 8, 10, 11 };
    
    /**
     * Room columns.
     */
    static final int[] ROOMS = { 3, 5, 7, 9 };

    /**
     * Which part of the puzzle are we trying to solve?
     */
    int part;
    
    /**
     * The state of the puzzle. Starts with the puzzle input. Letters move
     * around during processing.
     */
    char[][] map;

    /**
     * The number of amphipods in all four rooms. Array contains more entries
     * than necessary for easier processing.
     */
    int[] podsInRoom = { 0, 0, 0, 4, 0, 4, 0, 4, 0, 4, 0, 0, 0 };

    /**
     * The number of amphipods that are already at home in each of the four
     * rooms. Only amphipods that have no other types below them count. Puzzle
     * is solved when this number is 4 for each room. Again, more entries than
     * necessary for easier processing.
     */
    int[] podsAtHome;

    /**
     * The badness is an estimate of how much work is ahead (in steps * energy).
     * It just looks at the difference between an amphipod's current column and
     * the column of its target room, so it is a bit smaller than the actual
     * work, but still useful. When all amphipods are in place this value
     * becomes 0.
     */
    int badness;
    
    /**
     * The current solution we are trying. Each entry contains a movement,
     * encoded as "from * 12 + to", with '-' denoting an upwards move and '+'
     * a downwards one.
     */
    int[] solution = new int[32];
    
    /**
     * The best solution so far. Encoding is the same as above.
     */
    int[] bestSolution = new int[32];
    
    /**
     * The total energy cost of our currently best solution.
     */
    int bestCost = Integer.MAX_VALUE;

    /**
     * The number of steps of our currently best solution.
     */
    int bestSteps;
    
    /**
     * Creates (and sets up) our puzzle for the given part. Calculates the
     * initial badness.
     */
    Puzzle(int part) {
        this.part = part;
        
        if (part == 1) {
            map = new char[][] {
                "#############".toCharArray(),
                "#...........#".toCharArray(),
                "###C#D#A#B###".toCharArray(),
                "  #B#A#D#C#  ".toCharArray(),
                "  #A#B#C#D#  ".toCharArray(),
                "  #A#B#C#D#  ".toCharArray(),
                "  #########  ".toCharArray()
            };
            
            podsAtHome = new int[] { 0, 0, 0, 2, 0, 2, 0, 2, 0, 2, 0, 0, 0 };

        } else {
            map = new char[][] {
                "#############".toCharArray(),
                "#...........#".toCharArray(),
                "###C#D#A#B###".toCharArray(),
                "  #D#C#B#A#  ".toCharArray(),
                "  #D#B#A#C#  ".toCharArray(),
                "  #B#A#D#C#  ".toCharArray(),
                "  #########  ".toCharArray()
            };
        
            podsAtHome = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        }
        
        badness = 0;
        for (int i = 2; i < 6; i++) {
            for (int j = 3; j <= 9; j = j + 2) {
                char c = map[i][j];
                badness += getCost(c) * Math.abs(j - getHome(c));
            }
        }
    }
    
    /**
     * Dumps the map.
     */
    void dump() {
        for (char[] c: map) {
            System.out.println("  " + new String(c));
        }
    }

    /**
     * Returns the home column for the given type of amphipod.
     */
    int getHome(char c) {
        return switch (c) {
            case 'A' ->  3;
            case 'B' ->  5;
            case 'C' ->  7;
            case 'D' ->  9;
            default  -> -1;
        };
    }

    /**
     * Returns the energy cost per step for the given type of amphipod.
     */
    int getCost(char c) {
        return switch (c) {
            case 'A' ->  1;
            case 'B' ->  10;
            case 'C' ->  100;
            case 'D' ->  1000;
            default  -> -1;
        };
    }

    /**
     * Checks whether the hallway is free across the given range.
     */
    boolean isFree(int from, int to) {
        if (from < to) {
            for (int i = from + 1; i <= to; i++) {
                if (map[1][i] != '.') {
                    return false;
                }
            }
        } else {
            for (int i = to; i < from; i++) {
                if (map[1][i] != '.') {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    /**
     * Checks if the given move from a room to the hallway is possible.
     */
    boolean canMoveOut(int from, int to) {
        return podsInRoom[from] > podsAtHome[from] && isFree(from, to);
    }
    
    /**
     * Checks if the given move from the hallway to a room is possible.
     */
    boolean canMoveIn(int from, int to) {
        return podsInRoom[to] == podsAtHome[to] && getHome(map[1][from]) == to && isFree(from, to);
    }
    
    /**
     * Moves an amphipod out of a room into the hallway, adjusting the badness
     * and returning the cost of this move.
     */
    int moveOut(int from, int to) {
        int row = 6 - podsInRoom[from];
        char c = map[row][from];
        
        map[1][to] = c;
        map[row][from] = '.';
        podsInRoom[from]--;
        
        badness -= getCost(c) * Math.abs(getHome(c) - from);
        badness += getCost(c) * Math.abs(getHome(c) - to);
        
        return getCost(c) * (Math.abs(to - from) + row - 1);
    }
    
    /**
     * Moves an amphipod out of the hallway into a room, adjusting the badness
     * and returning the cost of this move.
     */
    int moveIn(int from, int to) {
        int row = 5 - podsInRoom[to];
        char c = map[1][from];
        
        map[1][from] = '.';
        map[row][to] = c;
        podsInRoom[to]++;
        
        badness -= getCost(c) * Math.abs(getHome(c) - from);
        badness += getCost(c) * Math.abs(getHome(c) - to);
        
        return getCost(c) * (Math.abs(to - from) + row - 1);
    }

    /**
     * Performs the recursive puzzle solving. Tries all movements that are
     * possible on the current state, calling itself after each one.
     */
    void process(int step, int cost) {
        if (cost + badness >= bestCost) {
            return;
        }
        
        if (badness == 0) {
            System.out.printf("\033[1APart %d: Best solution requires %d energy in %d steps.\n",
                    part, cost, step);
            
            bestCost = cost;
            bestSteps = step;
            bestSolution = new int[step];
            
            System.arraycopy(solution, 0, bestSolution, 0, step);
            
            return;
        }

        for (int i: HALLWAY) {
            for (int j: ROOMS) {
                if (canMoveOut(j ,i)) {
                    solution[step] = -(12 * j + i);
                    int extra = moveOut(j, i);
                    process(step + 1, cost + extra);
                    moveIn(i, j);
                } else if (canMoveIn(i, j)) {
                    solution[step] = 12 * i + j;
                    int extra = moveIn(i, j);
                    podsAtHome[j]++;
                    process(step + 1, cost + extra);
                    moveOut(j, i);
                    podsAtHome[j]--;
                }
            }
        }
    }
    
    /**
     * Shows an animation of the best solution.
     */
    void animate() {
        System.out.println();
        dump();
        
        for (int i = 0; i < bestSteps; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ignored) {
                // ...
            }
            
            int j = bestSolution[i];
            if (j < 0) {
                j = - j;
                moveOut(j / 12, j % 12);
            } else {
                moveIn(j / 12, j % 12);
            }
            
            System.out.print("\033[7A");
            dump();
        }

        System.out.println(); 
    }
    
    /**
     * Entry point.
     */
    public static void main(String[] args) {
        System.out.println();
        System.out.println("*** AoC 2021.23 Amphipod ***");
        System.out.println();

        System.out.println();
        
        Puzzle p1 = new Puzzle(1);
        p1.process(0, 0);
        p1.animate();

        System.out.println();
        
        Puzzle p2 = new Puzzle(2);
        p2.process(0, 0);
        p2.animate();  
    }
    
}
