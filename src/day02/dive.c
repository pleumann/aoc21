/**
 * Dive parts 1 and 2. Derived from Java version.
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

int length1, depth1;

int length2, depth2, aim2;

void part1(char *cmd, int arg) {
    switch (cmd[0]) {
        case 'u': depth1 = depth1 - arg; break;
        case 'd': depth1 = depth1 + arg; break;
        case 'f': length1 = length1 + arg; break;
    }
}

void part2(char *cmd, int arg) {
    switch (cmd[0]) {
        case 'u': aim2 = aim2 - arg; break;
        case 'd': aim2 = aim2 + arg; break;
        case 'f': length2 = length2 + arg; depth2 = depth2 + aim2 * arg; break;
    }
}

/**
 * Processes the puzzle data from the given file "on the fly".
 */
void process(char *name) {
    FILE *fp = fopen(name, "r");
    if (fp == 0){
        printf("Error: File '%s' not found.\n", name);
        exit(1);
    }

    int count = 0;

    char cmd[10];
    int arg;

    while (!feof(fp)) {
        if (fscanf(fp, "%s %d\r", cmd, &arg) != 2) {
            printf("\nError: Line %d is bad.\n", count);
            exit(1);
        }

        part1(cmd, arg);
        part2(cmd, arg);
        
        count++;
        putchar('.');
    }

    fclose(fp);

    printf("\n\n%d lines read.\n\n", count);
}

int main(int argc, char *argv[]) {
    puts("");
    puts("*** AoC 2021.02 ***");
    puts("");

    if (argc < 2) {
      puts("Usage: dive <values file>");
      exit(1);
    }

    process(argv[1]);

    printf("Part 1: length=%4d, depth=%6d, result=%10d\n", length1, depth1, length1 * depth1);
    printf("Part 2: length=%4d, depth=%6d, result=%10d\n", length2, depth2, length2 * depth2);

    puts("");
}
