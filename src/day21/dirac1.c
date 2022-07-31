/**
 * Dirac Dice part 1. See Java solution for comments.
 *
 * Compile for CP/M target using
 *
 *   zcc +cpm -v -Cz"--clean" -create-app dirac1.c -o dirac1.com -compiler=sdcc -SO3 --max-allocs-per-node200000
 *
 * or for native Next target using
 *
 *   zcc +zxn -v -clib=classic -lesxdos -Cz"--clean" -subtype=dot -create-app dirac1.c -o dirac1.dot -compiler=sdcc -SO3 --max-allocs-per-node200000
 *
 */
#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

#ifdef Z80
#pragma printf = "%u %llu"
#endif

uint16_t dice = 0;

uint16_t rolls = 0;

uint8_t positions[2] = { 2, 6 }; // 3, 7 for example

uint16_t scores[2] = { 0, 0 };

uint8_t roll() {
    putchar('.');

    rolls++;
    dice++;
    if (dice > 100) {
        dice = 1;
    }

    return dice;
}

static void simulate() {
    uint8_t player = 0;

    while (1) {
        uint16_t points = roll() + roll() + roll();

        positions[player] = (positions[player] + points) % 10;
        scores[player] = scores[player] + positions[player] + 1;

        if (scores[player] >= 1000) {
            break;
        }

        player = 1 - player;
    }
}   

int main(void) {
    puts("");
    puts("*** AoC 2022.21 'Dirac Dice' part 1 ***");
    puts("");

    simulate();

    puts("");
    puts("");

    for (int i = 0; i < 2; i++) {
        printf("Player %u has %u points @ %u rolls, product is %lu\n", 
                i, scores[i], rolls, (uint32_t)scores[i] * rolls);
    }
    puts("");
}

