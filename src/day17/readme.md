# ZX Spectrum Next

The ZX Spectrum Next solution for day 17 is implemented in Pascal. It can only
be compiled with a homebrew compiler I am working on (and that is not yet public
and probably never will be). So you can look at the source code, but not really
change it. You can execute the `trick.dot` program, though, after transferring
it to the Next (no `input.txt` needed). I recommend to enter `RUN AT 3` before
the dot command to get full speed. Assuming `trick.dot` resides in the root
directory of `c:` this would be something like:

```
RUN AT 3
./trick.dot
```

The program doesn't animate the individual shots themselves, but the result of
each as a function of initial x/y velocity. White point means we hit. Every
other color means we either undershot, overshot or shot "through" the target
area without actually hitting it. I simply used the absolute value of how far
the shot missed as an index into the standard palette and did not care about the
actual colors. The result shows an interesting pattern.

The original Java solution does something similar, by the way, but reduced to
four different characters representing the outcomes. If you zoom out heavily in
your console window you should see the pattern, too.