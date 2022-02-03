/**
 * Hydrothermal Venture parts 1 and 2.
 *
 * Compile for ZX Spectrum Next .dot target:
 *
 * zcc +zxn -subtype=dotn -clib=sdcc_iy -v -startup=30 -SO3 \
 *    --max-allocs-per-node200000 --opt-code-speed          \
 *    -Cz"--clean" hydro.c -o hydro -create-app
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdarg.h>
#include <arch/zxn.h>
#include <arch/zxn/esxdos.h>
#include <intrinsic.h>
#include <input.h>

#ifdef Z80
#pragma scanf = "%d"
#pragma printf = "%s %d"
#pragma output CLIB_EXIT_STACK_SIZE = 1
#endif

// === Error handling and BREAKing =============================================

char message[24];

void error(char *fmt, ...) {
    va_list vargs;
    va_start(vargs, fmt);
    vsnprintf(message, sizeof(message), fmt, vargs);
    va_end(vargs);

    message[strlen(message) - 1] += 0x80;
   
    exit((int)message);
}

void keys() {
    if (in_key_pressed(IN_KEY_SCANCODE_SPACE | 0x8000)) {
        while (in_key_pressed(IN_KEY_SCANCODE_SPACE | 0x8000));

        error("Break pressed");
    }
}

// === Setup and teardown ======================================================

int old_speed;

struct esx_mode old_mode;

unsigned char old_layer;

void init() {
    old_speed = ZXN_READ_REG(REG_TURBO_MODE);
    ZXN_NEXTREG(REG_TURBO_MODE, 3);

    esx_ide_mode_get(&old_mode);
    old_layer = old_mode.mode8.layer;
    old_mode.mode8.layer = 2;
    esx_ide_mode_set(&old_mode);
}

void done() {
    old_mode.mode8.layer = old_layer;
    esx_ide_mode_set(&old_mode);

    ZXN_NEXTREGA(REG_TURBO_MODE, old_speed);
}

// === General purpose helpers =================================================

#define z80_bpoke(a,b)  (*(unsigned char *)(a) = b)
#define z80_bpeek(a)    (*(unsigned char *)(a))

int min(int x, int y) {
    return x < y ? x : y;
}

int max(int x, int y) {
    return x > y ? x : y;
}

void swap(int *x, int *y) {
    int t = *x;
    *x = *y;
    *y = t;
}

// === File I/O helpers ========================================================

char esxbuf[128];
int esxpos = sizeof(esxbuf);
int esxlen = sizeof(esxbuf);
char file;

#define FILE char

char nextchar() {
    if (esxpos == esxlen) {
        if (esxlen < sizeof(esxbuf)) {
            return 0;
        }

        esxlen = esxdos_f_read(file, esxbuf, sizeof(esxbuf));
        esxpos = 0;
    }

    return esxbuf[esxpos++];
}

FILE *myopen(char *name, char *mode) {
    file = esx_f_open(name, ESX_MODE_OPEN_EXIST | ESX_MODE_R);
    if (file == 0xff) {
        return NULL;
    }

    return &file;
}

char *mygets(char *str, int len, FILE *dummy) {
    char c = nextchar();
    if (c == 0 || c == 26) {
        return NULL;
    }

    int pos = 0;

    while (c >= ' ' && pos < len - 1) {
        str[pos++] = c;
        c = nextchar();
    }

    str[pos] = 0;

    return str;
}

void myclose(FILE *dummy) {
    esx_f_close(file);
}

#define fopen myopen
#define fgets mygets
#define fclose myclose

// === Graphics helpers ========================================================

void plot(unsigned int x, unsigned int y, unsigned int c) {
    intrinsic_di();

    unsigned char old = ZXN_READ_MMU6();

    ZXN_WRITE_MMU6(18 + y / 32);

    y = 49152 + ((y & 31) << 8) + x;
    z80_bpoke(y, c);

    ZXN_WRITE_MMU6(old);

    intrinsic_ei();
}

void line(int x1, int y1, int x2, int y2, int c) {
    if (y1 == y2) {
        for (int i = x1; i <= x2; i++) {
            plot(i, y1, c); 
        }
    } else if (x1 == x2) {
        for (int i = y1; i <= y2; i++) {
            plot(x1, i, c); 
        }
    }
}

void ink(unsigned char c) {
    putchar(16); putchar(c);
}

void paper(unsigned char c) {
    putchar(17); putchar(c);
}

void at(unsigned char row, unsigned char col) {
    putchar(22); putchar(row); putchar(col);
}

void cls() {
    putchar(14);
}

void title(char *text) {
    at(0, 0); paper(0); ink(255); printf("%-32s", text);

    for (int i = 0; i < 8; i++) {
      line(208 + i, 7 - i, 215 + i, 7 - i, 224);
      line(216 + i, 7 - i, 223 + i, 7 - i, 252);
      line(224 + i, 7 - i, 231 + i, 7 - i, 28);
      line(232 + i, 7 - i, 239 + i, 7 - i, 31);
    }
}

void box(unsigned char row, unsigned char col, unsigned char width, char *title, char *msg) {
    at(row, col); paper(0); ink(255); printf("%s", title);
    for (int i = strlen(title); i < width; i++) {
        putchar(' ');
    }

    at(row + 1, col); paper(255); ink(0); printf("%s", msg);
    for (int i = strlen(msg); i < width; i++) {
        putchar(' ');
    }

    int left = col * 8 - 1;
    int right = (col + width) * 8;
    int top = row * 8;
    int bottom = row * 8 + 16;

    line(left, top, left, bottom, 0);
    line(right, top, right, bottom, 0);
    line(left, bottom, right, bottom, 0);
}

// === Low-level helpers for AoC puzzle ========================================

void clear() {
    intrinsic_di();

    uint8_t old = ZXN_READ_MMU6();

    for (uint8_t i = 20; i < 24; i++) {
        ZXN_WRITE_MMU6(i);
        memset((char*)0xc000, 255, 8192);
    }

    ZXN_WRITE_MMU6(old);

    intrinsic_ei();
}

void pixel(unsigned int x, unsigned int y, unsigned int c, int *count) {
    if (x > 255 || y > 191) {
        error("Bad coords: %d, %d", x, y);
    }

    intrinsic_di();

    unsigned char old = ZXN_READ_MMU6();

    y = 191 - y;

    ZXN_WRITE_MMU6(18 + y / 32);

    y = 49152 + ((y & 31) << 8) + x;

    unsigned char b = z80_bpeek(y);

    if (b != 0) {
        if (b != 255) {
            c = 0;
            (*count)++;
        }
        z80_bpoke(y, c);
    }

    ZXN_WRITE_MMU6(old);

    intrinsic_ei();
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
        at(3, 3); printf("%d", total);

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

        keys();
    }

    fclose(fp);
}

void horizontals(int xmin, int ymin) {
    for (int i = 0; i < numHorz; i++) {
        int x1 = horz[i].x1 - xmin;
        int y1 = horz[i].y1 - ymin;
        int x2 = horz[i].x2 - xmin;
        int y2 = horz[i].y2 - ymin;

        if (x2 < 0 || x1 > 255) {
            continue;
        }

        if ((y1 < 0) && (y2 < 0) || (y1 > 127) && (y2 > 127)) {
            continue;
        }

        x1 = max(x1, 0);
        x2 = min(x2, 255);

        int c = nextcolor();

        int old = count1;

        for (int i = x1; i <= x2; i++) {
            pixel(i, y1, c, &count1);
        }

        if (count1 != old) {
            at(3, 19); printf("%d", count1);
            at(6, 19); printf("%d", count1 + count2);
        }

        keys();
    }
}

void verticals(int xmin, int ymin) {
    for (int i = 0; i < numVert; i++) {
        int x1 = vert[i].x1 - xmin;
        int y1 = vert[i].y1 - ymin;
        int x2 = vert[i].x2 - xmin;
        int y2 = vert[i].y2 - ymin;

        if (x2 < 0 || x1 > 255) {
            continue;
        }

        if ((y1 < 0) && (y2 < 0) || (y1 > 127) && (y2 > 127)) {
            continue;
        }

        y1 = max(y1, 0);
        y2 = min(y2, 127);

        int c = nextcolor();

        int old = count1;

        for (int i = y1; i <= y2; i++) {
            pixel(x1, i, c, &count1);
        }

        if (count1 != old) {
            at(3, 19); printf("%d", count1);
            at(6, 19); printf("%d", count1 + count2);
        }

        keys();
    }
}

void diagonals(int xmin, int ymin) {
    for (int i = 0; i < numDiag; i++) {
        int x1 = diag[i].x1 - xmin;
        int y1 = diag[i].y1 - ymin;
        int x2 = diag[i].x2 - xmin;
        int y2 = diag[i].y2 - ymin;

        if (x2 < 0 || x1 > 255) {
            continue;
        }

        int dy = y2 > y1 ? 1 : -1;

        if (dy == 1) {
            int bl = max(0 - x1, 0 - y1);

            if (bl > 0) {
                x1 = x1 + bl;
                y1 = y1 + bl;
            }

            int tr = max(x2 - 255, y2 - 127);

            if (tr > 0) {
                x2 = x2 - tr;
                y2 = y2 - tr;
            }
        } else {
            int tl = max(0 - x1, y1 - 127);

            if (tl > 0) {
                x1 = x1 + tl;
                y1 = y1 - tl;
            }

            int br = max(x2 - 255, 0 - y2);

            if (br > 0) {
                x2 = x2 - br;
                y2 = y2 + br;
            }
        }

        if ((y1 < 0) && (y2 < 0) || (y1 > 127) && (y2 > 127)) {
            continue;
        }

        int c = nextcolor();

        int old = count2;

        int j = y1;
        for (int i = x1; i <= x2; i++) {
            pixel(i, j, c, &count2);
            j = j + dy;
        }

        if (count2 != old) {
            at(6, 19); printf("%d", count1 + count2);
        }

        keys();
    }
}

// === Main entry point ========================================================

int main(int argc, char **argv) {
    if (argc < 2) {
        puts("AoC 2021.05 Hydrothermal Venture");
        puts("");
        puts("Usage: .hydro <input.txt>");
        exit(0);
    }

    init(); atexit(done);

    paper(182); ink(0); cls();

    title("AoC 2021.05 Hydro Venture");

    box(2, 2, 12, "Lines total", " 0");
    box(5, 2, 12, "This sector", " 0,0");
    box(2, 18, 12, "Part 1", " 0");
    box(5, 18, 12, "Part 2", " 0");

    load(argv[1]);

    for (int i = 0; i < 1024; i = i + 256) {
        for (int j = 0; j < 1024; j = j + 128) {
           at(6, 3);
           printf("%d,%d   ", i, j);
           clear();

           horizontals(i, j);
           verticals(i, j);
           diagonals(i, j);
        }
    }

    at(23,31); puts("  ");

    return 0;
}

