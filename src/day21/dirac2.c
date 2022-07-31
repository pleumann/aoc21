/**
 * Dirac Dice part 2. See Java solution for comments.
 *
 * Compile for CP/M target using
 *
 *   zcc +cpm -v -Cz"--clean" -create-app dirac2.c -o dirac2.com -compiler=sdcc -SO3 --max-allocs-per-node200000
 *
 * or for native Next target using
 *
 *   zcc +zxn -v -clib=classic -lesxdos -Cz"--clean" -subtype=dot -create-app dirac2.c -o dirac2.dot -compiler=sdcc -SO3 --max-allocs-per-node200000
 */
#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

#ifdef Z80
#pragma printf = "%s %u %llu %d"
#endif

uint8_t frequencies[] = { 0, 0, 0, 1, 3, 6, 7, 6, 3, 1 };

uint8_t positions[] = { 2, 6 }; // 3, 7 for example

uint8_t scores[] = { 0, 0 };

uint64_t wins[] = { 0, 0 };

uint32_t count = 0;
    
void simulate(uint8_t player, uint8_t points, uint64_t factor) {
    if (count++ == 100000) {
        putchar('.');
        count = 0;
    }

    uint8_t savePosition = positions[player];
    uint8_t saveScore = scores[player];

    positions[player] = (positions[player] + points) % 10;
    scores[player] = scores[player] + positions[player] + 1;

    if (scores[player] >= 21) {
        wins[player] = wins[player] + factor;
    } else {
#ifdef Z80
        // Unrolling inner loop to avoid unnecessary long operations on Z80

        uint64_t factor3 = (factor << 1) + factor;
        uint64_t factor6 = factor3 << 1;
        uint64_t factor7 = factor6 + factor;

        simulate(1 - player, 3, factor);
        simulate(1 - player, 4, factor3);
        simulate(1 - player, 5, factor6);
        simulate(1 - player, 6, factor7);
        simulate(1 - player, 7, factor6);
        simulate(1 - player, 8, factor3);
        simulate(1 - player, 9, factor);
#else
        for (uint8_t i = 3; i <= 9; i++) {
            simulate(1 - player, i, factor * frequencies[i]);
        }
#endif
    }

    positions[player] = savePosition;
    scores[player] = saveScore;
}

int main(void) {
    puts("");
    puts("*** AoC 2022.21 'Dirac Dice' part 2 ***");
    puts("");

    for (uint8_t i = 3; i <= 9; i++) {
        simulate(0, i, frequencies[i]);
    }

    puts("");
    puts("");

    for (int i = 0; i < 2; i++) {
        printf("Player %d wins in %llu universes\n", i, wins[i]);
    }

    puts("");
}
