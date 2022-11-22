/**
 * Binary diagnostic parts 1 and 2. Derived from Java version.
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdarg.h>

/**
 * Result for part 1.
 */
int part1 = 0;

/**
 * Result for part 2.
 */
int part2 = 0;

// === General helpers =========================================================

/**
 * Throws and error and exits.
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
 * Returns the number of 1 bits in the given integer.
 */
int bitCount(int i) {
    int j = 0;
    
    while (i != 0) {
        if ((i & 1) != 0) {
            j++;
        }
        i = i >> 1;
    }

    return j;
}

/**
 * Converts a signal string into a bit pattern (a->1, b->2, c->4, ...).
 */
int toBits(char *s) {
    int i = 0;
    int j = 0;

    while (s[i] != '\0') {
        j = j | (1 << (s[i] - 'a'));
        i++;
    }

    return j;
}

/**
 * Converts a bit pattern into a signal string (1->a, 2->b 4->c, ...). Uses a
 * static buffer, so the result is only valid up to the next function call.
 */
char *toChars(int i) {
    static char buffer[10];
    int j = 0;

    char c = 'a';
    while (i != 0) {
        if ((i & 1) == 1) {
            buffer[j++] = c;
        }
        c++;
        i = i >> 1;
    }

    buffer[j] = '\0';

    return buffer;
}

// === Puzzle logic ============================================================

/**
 * Finds in a list of signal combinations the one that contains "length"
 * signals and is a proper superset of "include" (i.e. contains it fully).
 */
int find(int wires[], int length, int include) {
    for (int i = 0; i < 10; i++) {
        int s = wires[i];

        if (s == 0) continue;
        if (bitCount(s) != length) continue;                
        if ((s & include) != include) continue;

//        puts(toChars(s));
        wires[i] = 0;

        return s;
    }

    error("Oops!");

    return 0;
}

/**
 * Solves one line of input.
 */
void analyze(int wires[], int numbers[]) {
    int digits[10];

    // Derive digits from wires and signals...

    digits[1] = find(wires, 2, 0);         // 2 segments, unique 
    digits[4] = find(wires, 4, 0);         // 4 segments, unique
    digits[7] = find(wires, 3, 0);         // 3 segments, unique
    digits[8] = find(wires, 7, 0);         // 7 segments, unique

    digits[3] = find(wires, 5, digits[1]); // 5 segments, contains all of '1'
    digits[9] = find(wires, 6, digits[4]); // 6 segments, contains all of '4'
    digits[0] = find(wires, 6, digits[1]); // 6 segments, contains all of '1'
    digits[6] = find(wires, 6, 0);         // 6 segments, only one left

    digits[2] = find(wires, 5, digits[8] ^ digits[6]);
                                           // 5 segments, contains '8 xor 6'

    digits[5] = find(wires, 5, 0);         // 5 segments, only one left

    for (int i = 0; i < 10; i++) {
        printf("%d -> %s\n", i, toChars(digits[i]));
    }

    // Calculate 4-digit number that is shown...

    int n = 0;
    for (int i = 0; i < 4; i++) {
        for (int j = 0; j < 10; j++) {
            if (numbers[i] == digits[j]) {
                n = n * 10 + j;
                printf("%-7s = %d\n", toChars(numbers[i]), j);
            }
        }
    }

    printf("%d\n", n);

    part2 += n;
}

/**
 * Solves one line of input.
 */
void processLine(char *str) {
    char *left = &str[0];
    char *right = &str[61];

    int wires[10];
    int digits[4];

    printf("%s", str);

    str[58] = '\0';

    int i = 0;
    char *pc = strtok(left, " \n");
    while (pc != NULL) {
        wires[i++] = toBits(pc);
        pc = strtok(NULL, " \n");
    }

    int j = 0;
    pc = strtok(right, " \n");
    while (pc != NULL) {
        int bits = toBits(pc);
        int count = bitCount(bits);
        if (count == 2 || count == 3 || count == 4 || count == 7) {
            part1++;
        }

        digits[j] = toBits(pc);
        j++;

        pc = strtok(NULL, " \n");
    }

    analyze(wires, digits);

    puts("");
}

/**
 * Processes the given file.
 */
void process(char *filename) {
    FILE *fp;
    char str[128];

    fp = fopen(filename, "r");
    if (fp == 0){
        error("File '%s' not found", filename);
    }

    int total = 0;

    while (fgets(str, 128, fp) != NULL) {
        processLine(str);
    }

    fclose(fp);
}

// === Main entry point ========================================================

int main(int argc, char **argv) {
    puts("");
    puts("*** AoC 2021.08 Binary Diagnostic ***");
    puts("");

    if (argc < 2) {
        puts("Usage: binary <input.txt>");
        exit(0);
    }

    process(argv[1]);

    printf("Part 1: %d\n", part1);
    printf("Part 2: %d\n", part2);

    puts("");

    return 0;
}


