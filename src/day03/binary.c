/**
 * Binary Diagnostic parts 1 and 2. Derived from Java version.
 *
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

/**
 * Holds the file contents as individual strings.
 */
char values[1000][20];

/**
 * Holds the number of lines in the file (i.e. the 'height').
 */
int count = 0;

/**
 * Holds the length of each line (i.e. the 'width')
 */
int numBits;

/**
 * Loads the puzzle data from the given file.
 */
void load(char *name) {
    FILE *fp;
 
    fp = fopen(name, "r");
    if (fp == 0){
        printf("Error: File '%s' not found.\n", name);
        exit(1);
    }

    int total = 0;

    while (!feof(fp)) {
        int code = fscanf(fp, "%s\r", values[count++]);
        if (code != 1) {
            printf("\nError: Line %d is bad.\n", count);
            exit(1);
        }
        
        putchar('.');
    }

    numBits = strlen(values[0]);

    fclose(fp);

    printf("\n\n%d lines read, number of bits is %d.\n\n", count, numBits);
}

/**
 * Checks if the '1' bit is dominant in the given list of values words at the
 * given column.
 */
bool isOneDominant(int place) {
    int ones = 0;
    int zeros = 0;

    for (int i = 0; i < count; i++) {
        if (values[i][place] == '1') {
            ones++;
        } else {
            zeros++;
        }
    }

    return ones >= zeros;
}

/**
 * Calculates epsilon and gamma, then power demand.
 */
int getPowerDemand() {
    int epsilon = 0;
    int gamma = 0;

    for (int i = 0; i < numBits; i++) {
        epsilon = epsilon << 1;
        gamma = gamma << 1;

        if (isOneDominant(i)) {
            epsilon = epsilon | 1;
        } else {
            gamma = gamma | 1;
        }
    }

    return epsilon * gamma;
}

/**
 * Checks if the '1' bit is dominant in the given list of values words at the
 * given column. This version does take an array of deleted entries into
 * account (by ignoring those).
 */
bool isOneDominantWithDeleted(int place, bool deleted[2000]) {
    int ones = 0;
    int zeros = 0;

    for (int i = 0; i < count; i++) {
        if (!deleted[i]) {
            if (values[i][place] == '1') {
                ones++;
            } else {
                zeros++;
            }
        }
    }

    return ones >= zeros;
}

/**
 * Converts a string containing a binary number to the corresponding integer
 * value.
 */
int parsebin(char *s) {
    int i = 0;

    for (int j = 0; j < strlen(s); j++) {
        i = (i << 1) | (s[j] == '1' ? 1 : 0);
    }

    return i;
}

/**
 * Repeatedly removes lines from the input according to the rules of part 2
 * until only a single word remains. To be called with '1', '0' for Oxygen
 * and '0', '1' for Carbon. Slightly inefficient compared to the Java version,
 * but we don't have dynamic arrays and we don't want to manage memory.
 */
int filter(char first, char second) {
    bool deleted[2000];
    int remaining = count;

    for (int i = 0; i < count; i++) {
        deleted[i] = false;
    }

    int bit = 0;
    while (remaining != 1) {
        char c = isOneDominantWithDeleted(bit, deleted) ? first : second;
        for (int i = 0; i < count; i++) {
            if (!deleted[i] && values[i][bit] != c) {
                deleted[i] = true;
                remaining--;
            }
        }

        bit++;
    }

    for (int i = 0; i < count; i++) {
        if (!deleted[i]) {
            return parsebin(values[i]);
        }
    }

    return -1;
}

/**
 * Calculates oxygen and carbon, then returns life support.
 */
int getLifeSupport() {
    int oxygen = filter('1', '0');
    int carbon = filter('0', '1');

    return oxygen * carbon;
}

int main(int argc, char *argv[]) {
    puts("");
    puts("*** AoC 2021.03 ***");
    puts("");

    if (argc < 2) {
      puts("Usage: binary <values file>");
      exit(1);
    }

    load(argv[1]);

    printf("Part 1: Power demand is %d\n", getPowerDemand());
    printf("Part 2: Life support is %d\n", getLifeSupport());

    puts("");
}

