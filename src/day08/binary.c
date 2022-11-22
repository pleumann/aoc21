/**
 * Binary diagnostic parts 1 and 2.
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdarg.h>

void error(char *fmt, ...) {
    va_list vargs;
    va_start(vargs, fmt);
    printf(fmt, vargs);
    va_end(vargs);

    puts("");
    exit(1);
}

int part1 = 0;

int part2 = 0;

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
 * Converts a bit pattern into a signal string (1->a, 2->b 4->c, ...).
 */
char* toChars(int i, char *s) {
    int j = 0;

    char c = 'a';
    while (i != 0) {
        if ((i & 1) == 1) {
            s[j++] = c;
        }
        c++;
        i = i >> 1;
    }

    s[j] = '\n';

    return s;
}


void process(char *filename) {
    FILE *fp;
    char str[128];
    char *left = &str[0];
    char *right = &str[61];

    char buffer[8];

    int x1, y1, x2, y2, tmp;

    fp = fopen(filename, "r");
    if (fp == 0){
        error("File '%s' not found", filename);
    }

    int total = 0;

    while (fgets(str, 128, fp) != NULL) {
        str[58] = '\0';
        total++;

        puts(left);
        puts(right);

        char *pc = strtok(right," \n");
        while (pc != NULL) {
            int l = bitCount(toBits(pc));
            printf("%s -> %s\n", pc, toChars(toBits(pc), buffer));
            if (l == 2 || l == 3 || l == 4 || l == 7) {
                part1++;
            }

            pc = strtok(NULL, " \n");
        }

    }

    fclose(fp);

    puts("");
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

    puts("");

    printf("Part 1: %d\n", part1);
    printf("Part 2: %d\n", part2);

    puts("");

    return 0;
}


