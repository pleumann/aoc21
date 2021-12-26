package day24;

/**
 * Day 24 "Arithmetic Logic Unit". Generated code.
 */
class Monad {

    static int run(int index, int z, int input) {

        int w = 0;
        int x = 0;
        int y = 0;

        switch (index) {
            case 0 -> {
                w = input;
                x *= 0;
                x += z;
                x %= 26;
                z /= 1;
                x += 11;
                x = (x == w) ? 1 : 0;
                x = (x == 0) ? 1 : 0;
                y *= 0;
                y += 25;
                y *= x;
                y += 1;
                z *= y;
                y *= 0;
                y += w;
                y += 8;
                y *= x;
                z += y;
            }
            case 1 -> {
                w = input;
                x *= 0;
                x += z;
                x %= 26;
                z /= 1;
                x += 14;
                x = (x == w) ? 1 : 0;
                x = (x == 0) ? 1 : 0;
                y *= 0;
                y += 25;
                y *= x;
                y += 1;
                z *= y;
                y *= 0;
                y += w;
                y += 13;
                y *= x;
                z += y;
            }
            case 2 -> {
                w = input;
                x *= 0;
                x += z;
                x %= 26;
                z /= 1;
                x += 10;
                x = (x == w) ? 1 : 0;
                x = (x == 0) ? 1 : 0;
                y *= 0;
                y += 25;
                y *= x;
                y += 1;
                z *= y;
                y *= 0;
                y += w;
                y += 2;
                y *= x;
                z += y;
            }
            case 3 -> {
                w = input;
                x *= 0;
                x += z;
                x %= 26;
                z /= 26;
                x += 0;
                x = (x == w) ? 1 : 0;
                x = (x == 0) ? 1 : 0;
                y *= 0;
                y += 25;
                y *= x;
                y += 1;
                z *= y;
                y *= 0;
                y += w;
                y += 7;
                y *= x;
                z += y;
            }
            case 4 -> {
                w = input;
                x *= 0;
                x += z;
                x %= 26;
                z /= 1;
                x += 12;
                x = (x == w) ? 1 : 0;
                x = (x == 0) ? 1 : 0;
                y *= 0;
                y += 25;
                y *= x;
                y += 1;
                z *= y;
                y *= 0;
                y += w;
                y += 11;
                y *= x;
                z += y;
            }
            case 5 -> {
                w = input;
                x *= 0;
                x += z;
                x %= 26;
                z /= 1;
                x += 12;
                x = (x == w) ? 1 : 0;
                x = (x == 0) ? 1 : 0;
                y *= 0;
                y += 25;
                y *= x;
                y += 1;
                z *= y;
                y *= 0;
                y += w;
                y += 4;
                y *= x;
                z += y;
            }
            case 6 -> {
                w = input;
                x *= 0;
                x += z;
                x %= 26;
                z /= 1;
                x += 12;
                x = (x == w) ? 1 : 0;
                x = (x == 0) ? 1 : 0;
                y *= 0;
                y += 25;
                y *= x;
                y += 1;
                z *= y;
                y *= 0;
                y += w;
                y += 13;
                y *= x;
                z += y;
            }
            case 7 -> {
                w = input;
                x *= 0;
                x += z;
                x %= 26;
                z /= 26;
                x += -8;
                x = (x == w) ? 1 : 0;
                x = (x == 0) ? 1 : 0;
                y *= 0;
                y += 25;
                y *= x;
                y += 1;
                z *= y;
                y *= 0;
                y += w;
                y += 13;
                y *= x;
                z += y;
            }
            case 8 -> {
                w = input;
                x *= 0;
                x += z;
                x %= 26;
                z /= 26;
                x += -9;
                x = (x == w) ? 1 : 0;
                x = (x == 0) ? 1 : 0;
                y *= 0;
                y += 25;
                y *= x;
                y += 1;
                z *= y;
                y *= 0;
                y += w;
                y += 10;
                y *= x;
                z += y;
            }
            case 9 -> {
                w = input;
                x *= 0;
                x += z;
                x %= 26;
                z /= 1;
                x += 11;
                x = (x == w) ? 1 : 0;
                x = (x == 0) ? 1 : 0;
                y *= 0;
                y += 25;
                y *= x;
                y += 1;
                z *= y;
                y *= 0;
                y += w;
                y += 1;
                y *= x;
                z += y;
            }
            case 10 -> {
                w = input;
                x *= 0;
                x += z;
                x %= 26;
                z /= 26;
                x += 0;
                x = (x == w) ? 1 : 0;
                x = (x == 0) ? 1 : 0;
                y *= 0;
                y += 25;
                y *= x;
                y += 1;
                z *= y;
                y *= 0;
                y += w;
                y += 2;
                y *= x;
                z += y;
            }
            case 11 -> {
                w = input;
                x *= 0;
                x += z;
                x %= 26;
                z /= 26;
                x += -5;
                x = (x == w) ? 1 : 0;
                x = (x == 0) ? 1 : 0;
                y *= 0;
                y += 25;
                y *= x;
                y += 1;
                z *= y;
                y *= 0;
                y += w;
                y += 14;
                y *= x;
                z += y;
            }
            case 12 -> {
                w = input;
                x *= 0;
                x += z;
                x %= 26;
                z /= 26;
                x += -6;
                x = (x == w) ? 1 : 0;
                x = (x == 0) ? 1 : 0;
                y *= 0;
                y += 25;
                y *= x;
                y += 1;
                z *= y;
                y *= 0;
                y += w;
                y += 6;
                y *= x;
                z += y;
            }
            case 13 -> {
                w = input;
                x *= 0;
                x += z;
                x %= 26;
                z /= 26;
                x += -12;
                x = (x == w) ? 1 : 0;
                x = (x == 0) ? 1 : 0;
                y *= 0;
                y += 25;
                y *= x;
                y += 1;
                z *= y;
                y *= 0;
                y += w;
                y += 14;
                y *= x;
                z += y;
            }
        }

        return z;
    }

}
