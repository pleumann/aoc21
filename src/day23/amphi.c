/**
 * Amphipod parts 1 and 2. Recursive depth-first traversal of the solution space
 * with some shortcuts to make it fast. Also, we treat part 1 with the machinery
 * of part 2 by simply putting two rows of "already in place" amphipod
 * underneath.
 *
 * This is derived from the original Java solution, trying to keep the structure
 * as close as possible.
 *
 * Compile for Windows / Linux / Mac using
 *
 *   gcc -o amphi -O3 amphi.c
 *
 */
#include <ctype.h>
#include <limits.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

/**
 * Which part are we trying to solve?
 */
int thisPart;

/**
 * The puzzle input, modified during processing.
 */
char map[7][14];

/**
 * Hallway columns where an amphipod may rest.
 */
int HALLWAY[7] = { 1, 2, 4, 6, 8, 10, 11 };
    
/**
 * Room columns.
 */
int ROOMS[4] = { 3, 5, 7, 9 };

/**
 * The number of amphipods in all four rooms. Array contains more entries
 * than necessary for easier processing.
 */
int podsInRoom[13] = { 0, 0, 0, 4, 0, 4, 0, 4, 0, 4, 0, 0, 0 };

/**
 * The number of amphipods that are already at home in each of the four
 * rooms. Only amphipods that have no other types below them count. Puzzle
 * is solved when this number is 4 for each room. Again, more entries than
 * necessary for easier processing.
 */
int podsAtHome[13];

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
int solution[32];
    
/**
 * The best solution so far. Encoding is the same as above.
 */
int bestSolution[32];
    
/**
 * The total energy cost of our currently best solution.
 */
int bestCost;

/**
 * The number of steps of our currently best solution.
 */
int bestSteps;


/**
 * Two "forward" declarations.
 */
int getHome(char c);
int getCost(char c);

/**
 * Creates (and sets up) our puzzle for the given part. Calculates the
 * initial badness.
 */
void setup(int part) {
    thisPart = part;

    if (part == 1) {
        char map1[7][14] = {
            "#############",
            "#...........#",
            "###C#D#A#B###",
            "  #B#A#D#C#  ",
            "  #A#B#C#D#  ",
            "  #A#B#C#D#  ",
            "  #########  "
        };

        int podsAtHome1[13] = { 0, 0, 0, 2, 0, 2, 0, 2, 0, 2, 0, 0, 0 };


        memcpy(map, map1, sizeof(map));
        memcpy(podsAtHome, podsAtHome1, sizeof(podsAtHome));
    } else {
        char map2[7][14] = {
            "#############",
            "#...........#",
            "###C#D#A#B###",
            "  #D#C#B#A#  ",
            "  #D#B#A#C#  ",
            "  #B#A#D#C#  ",
            "  #########  "
        };

        int podsAtHome2[13] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

        memcpy(map, map2, sizeof(map));
        memcpy(podsAtHome, podsAtHome2, sizeof(podsAtHome));
    }

    badness = 0;
    for (int i = 2; i < 6; i++) {
        for (int j = 3; j <= 9; j = j + 2) {
            char c = map[i][j];
            badness += getCost(c) * abs(j - getHome(c));
        }
    }

    bestCost = INT_MAX;
}
   
/**
 * Dumps the map.
 */
void dump() {
    for (int i = 0; i < 7; i++) {
        printf("  %s\n", map[i]);
    }
}

/**
 * Returns the home column for the given type of amphipod.
 */
int getHome(char c) {
    switch (c) {
        case 'A': return 3;
        case 'B': return 5;
        case 'C': return 7;
        case 'D': return 9;
    };

    return -1;
}

/**
 * Returns the energy cost per step for the given type of amphipod.
 */
int getCost(char c) {
    switch (c) {
        case 'A': return 1;
        case 'B': return 10;
        case 'C': return 100;
        case 'D': return 1000;
    };

    return -1;
}

/**
 * Checks whether the hallway if free in the given range.
 */
bool isFree(int from, int to) {
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
bool canMoveOut(int from, int to) {
    return podsInRoom[from] > podsAtHome[from] && isFree(from, to);
}

/**
 * Checks if the given move from the hallway to a room is possible.
 */
bool canMoveIn(int from, int to) {
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

    badness -= getCost(c) * abs(getHome(c) - from);
    badness += getCost(c) * abs(getHome(c) - to);

    return getCost(c) * (abs(to - from) + row - 1);
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

    badness -= getCost(c) * abs(getHome(c) - from);
    badness += getCost(c) * abs(getHome(c) - to);

    return getCost(c) * (abs(to - from) + row - 1);
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
        printf("\033[1APart %d: Best solution requires %d energy in %d steps.\n",
                thisPart, cost, step);

        bestCost = cost;
        bestSteps = step;

        memcpy(bestSolution, solution, sizeof(solution));

        return;
    }

    for (int h = 0; h < 7; h++) {
        int i = HALLWAY[h];
        for (int r = 0; r < 4; r++) {
            int j = ROOMS[r];
            if (canMoveOut(j, i)) {
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
    puts("");
    dump();

    for (int i = 0; i < bestSteps; i++) {
        clock_t startTime = clock();
        while(clock() < (startTime + 500 * 1000));

        int j = bestSolution[i];
        if (j < 0) {
            j = - j;
            moveOut(j / 12, j % 12);
        } else {
            moveIn(j / 12, j % 12);
        }

        printf("\033[7A");
        dump();
    }

    puts("");
}
    
/**
 * Entry point.
 */
int main(void) {
    puts("\n*** AoC 2021.23 Amphipod ***\n");

    puts("");

    setup(1);
    process(0, 0);
    animate();

    puts("");

    setup(2);
    process(0, 0);
    animate();  
}
