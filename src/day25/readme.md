# ZX Spectrum Next

For running the day 25 solution on your ZX Spectrum Next follow these steps:

 1. Copy `day25.bas`, `day25.rom` and `input.txt` to your Next SD card. They
    need to be in the same folder.

 2. On the Next, enter these commands:

```
LOAD "day25"
RUN
```

If you want to run the program on your own AoC input.txt you might have to
modify the Z80 assembly part (number of rows and colums), then translate again
using the assembler of your choice. For instance:

```
$ zasm day25.z80
```
