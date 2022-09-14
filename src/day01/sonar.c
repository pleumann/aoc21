/**
 * Sonar Sweep parts 1 and 2.
 *
 * Compile for CP/M .com target:
 *
 * zcc +cpm -v -Cz"--clean" -SO3 sonar.c -o sonar.com -create-app
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#ifdef Z80
#pragma scanf = "%d"
#pragma printf = "%d"
#endif

int input[2000];

int count = 0;

void load(char *name) {
    FILE *fp;
    char line[128];
 
    int x1, y1, x2, y2, tmp;

    fp = fopen(name, "r");
    if (fp == 0){
        printf("Error: File '%s' not found.\n", name);
        exit(1);
    }

    int total = 0;

    while (fgets(line, 128, fp) != NULL) {
        int code = sscanf(line, "%d", &input[count++]);
        if (code != 1) {
            printf("Error: Line %d is bad.\n", count);
            exit(1);
        }
    }

    fclose(fp);

    printf("%d lines read.\n\n", count);
}

void process(int part, int distance) {
    int result = 0;

    for (int i = distance; i < count; i++) {
        if (input[i] > input[i - distance]) {
            result++;
        }
    }

    printf("Part %d result is %d.\n", part, result);
}

int main(int argc, char *argv[]) {
    puts("");
    puts("*** AoC 2021.01 ***");
    puts("");

    if (argc < 2) {
      puts("Usage: sonar <input file>");
      exit(1);
    }

    load(argv[1]);

    process(1, 1);
    process(2, 3);

    puts("");
}
