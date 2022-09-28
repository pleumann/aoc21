/**
 * Hydrothermal Venture parts 1 and 2 for PC/Mac (or anything else that is able
 * to handle a 1 MB array).
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdarg.h>

// === Error handling ==========================================================

void error(char *fmt, ...) {
    va_list vargs;
    va_start(vargs, fmt);
    printf(fmt, vargs);
    va_end(vargs);

    puts("");
    exit(1);
}

// === General purpose helpers =================================================

void swap(int *x, int *y) {
    int t = *x;
    *x = *y;
    *y = t;
}

// === Low-level helpers for AoC puzzle ========================================

char memory[1024][1024];

void clear() {
    memset(memory[0], 0, sizeof(memory));
}

void pixel(unsigned int x, unsigned int y, int *count) {
    if (x > 1023 || y > 1023) {
        error("Bad coords: %d, %d", x, y);
    }

    unsigned char b = memory[x][y];

    if (b == 1) {
        (*count)++;
    }

    memory[x][y] =  b + 1;
}

// === Puzzle data structures and functions ====================================

#define SIZE 200

typedef struct {
    int x1, y1, x2, y2;
} LINE;

LINE horz[SIZE];

LINE vert[SIZE];

LINE diag[SIZE];

int numHorz = 0;

int numVert = 0;

int numDiag = 0;

int count1 = 0;

int count2 = 0;

unsigned int color = 1;

unsigned int nextcolor() {
    color = color + 5;
    if (color > 254) {
        color = 1;
    }

    return color;
}

void load(char *filename) {
    FILE *fp;
    char str[128];
 
    int x1, y1, x2, y2, tmp;

    fp = fopen(filename, "r");
    if (fp == 0){
        error("File '%s' not found", filename);
    }

    int total = 0;

    while (fgets(str, 128, fp) != NULL) {
        total++;
        putchar('.');

        int code = sscanf(str, "%d,%d -> %d,%d", &x1, &y1, &x2, &y2);
        if (code != 4) {
            error("Bad line '%s'", str);
        }

        if (x2 < x1) {
            swap(&x1, &x2);
            swap(&y1, &y2);
        }

        if (y1 == y2) {
            horz[numHorz].x1 = x1;
            horz[numHorz].y1 = y1;
            horz[numHorz].x2 = x2;
            horz[numHorz].y2 = y2;
            numHorz++;
            if (numHorz > SIZE) {
                error("%sal overflow", "Horizont");
            }
        } else if (x1 == x2) {
            if (y2 < y1) {
                swap(&y1, &y2);
            }

            vert[numVert].x1 = x1;
            vert[numVert].y1 = y1;
            vert[numVert].x2 = x2;
            vert[numVert].y2 = y2;
            numVert++;
            if (numVert > SIZE) {
                error("%sal overflow", "Vertic");
            }
        } else {
            diag[numDiag].x1 = x1;
            diag[numDiag].y1 = y1;
            diag[numDiag].x2 = x2;
            diag[numDiag].y2 = y2;
            numDiag++;
            if (numDiag > SIZE) {
                error("%sal overflow", "Diagon");
            }
        }
    }

    fclose(fp);

    puts("");
}

void horizontals() {
    for (int i = 0; i < numHorz; i++) {
        int x1 = horz[i].x1;
        int y1 = horz[i].y1;
        int x2 = horz[i].x2;
        int y2 = horz[i].y2;

        for (int i = x1; i <= x2; i++) {
            pixel(i, y1, &count1);
        }
    }
}

void verticals() {
    for (int i = 0; i < numVert; i++) {
        int x1 = vert[i].x1;
        int y1 = vert[i].y1;
        int x2 = vert[i].x2;
        int y2 = vert[i].y2;

        for (int i = y1; i <= y2; i++) {
            pixel(x1, i, &count1);
        }
    }
}

void diagonals() {
    for (int i = 0; i < numDiag; i++) {
        int x1 = diag[i].x1;
        int y1 = diag[i].y1;
        int x2 = diag[i].x2;
        int y2 = diag[i].y2;

        int dy = y2 > y1 ? 1 : -1;

        int j = y1;
        for (int i = x1; i <= x2; i++) {
            pixel(i, j, &count2);
            j = j + dy;
        }
    }
}

// === Main entry point ========================================================

int main(int argc, char **argv) {
    puts("");
    puts("*** AoC 2021.05 Hydrothermal Venture ***");
    puts("");

    if (argc < 2) {
        puts("Usage: .hydro32 <input.txt>");
        exit(0);
    }

    load(argv[1]);

    puts("");

    clear();
    horizontals();
    verticals();
    diagonals();

    puts("");

    printf("Part 1: %d\n", count1);
    printf("Part 2: %d\n", count1 + count2);

    puts("");

    return 0;
}

