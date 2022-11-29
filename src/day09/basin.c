/**
 * Smoke basin parts 1 and 2. Derived from Java version.
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdarg.h>
#include <stdbool.h>

/**
 * Prints error message and exits.
 */
void error(char *fmt, ...) {
    va_list vargs;
    va_start(vargs, fmt);
    printf(fmt, vargs);
    va_end(vargs);

    puts("");
    exit(1);
}

/**
 * Map size
 */
#define size 100

/**
 * Map plus 1 element border.
 */
int map[size + 2][size + 2] = {};

/**
 * Reads input file into 2D array.
 */
void load(char *filename) {
    FILE *fp;
    char str[128];

    fp = fopen(filename, "r");
    if (fp == 0){
        error("File '%s' not found", filename);
    }

    int i = 0;
    while (fgets(str, 128, fp) != NULL) {
        i++;

        putchar('.');

        for (int j = 1; j <= size; j++) {
            map[i][j] = str[j - 1] - '0';
        }

        map[0][i] = 9;        // Top border
        map[size + 1][i] = 9; // Bottom border
        map[i][0] = 9;        // Left border
        map[i][size + 1] = 9; // Right border
    }

    fclose(fp);

    printf("\n\n%d lines read.\n\n", i);
}

/**
 * Checks is given point is a low.
 */
bool isLow(int i, int j) {
    int depth = map[i][j];

    if (map[i - 1][j] <= depth) return false;
    if (map[i + 1][j] <= depth) return false;
    if (map[i][j - 1] <= depth) return false;
    if (map[i][j + 1] <= depth) return false;

    return true;
}

/**
 * Performs a recursive flood fill starting from the given point.
 */
int floodFill(int row, int column) {
    int count = 0;

    if (map[row][column] < 9) {
        map[row][column] = 9;

        count++;

        count += floodFill(row - 1, column);
        count += floodFill(row + 1, column);
        count += floodFill(row, column - 1);
        count += floodFill(row, column + 1);
    }

    return count;
}

/**
 * Solves both parts in a single go.
 */
void solve() {
    int level = 0;
    int basins[4] = {};

    for (int i = 1; i <= size; i++) {
        for (int j = 1; j <= size; j++) {
            if (isLow(i, j)) {
                level= level + map[i][j] + 1;

                basins[3] = floodFill(i, j);
                
                // Poor man's high score table sorting
                for (int k = 2; k >= 0; k--) {
                    if (basins[k] < basins[k + 1]) {
                        int temp = basins[k];
                        basins[k] = basins[k + 1];
                        basins[k + 1] = temp;
                    }
                }
            }
        }
    }

    printf("Risk level: %6d\n", level);
    printf("Basin size: %6d\n", basins[0] * basins[1] * basins[2]);
}

/**
 * Main entry point
 */
int main(int argc, char **argv) {
    puts("");
    puts("*** AoC 2021.09 Smoke Basin ***");
    puts("");

    if (argc < 2) {
        puts("Usage: basin <input.txt>");
        exit(0);
    }

    load(argv[1]);
    solve();

    puts("");
    
    return 0;
}



