package day02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Day 2 "Dive" part 1.
 */
public class Puzzle1 {
    
    int depth, length;

    void command(String s) {
        int p = s.indexOf(' ');

        char cmd = s.charAt(0);
        int arg = Integer.parseInt(s.substring(p + 1));

        switch (cmd) {
            case 'u' -> depth = depth - arg;
            case 'd' -> depth = depth + arg;
            case 'f' -> length = length + arg;
            case 'b' -> length = length - arg;
        }

        System.out.println(depth * length);
    }
    
    void process(String file) {
        try {
            new BufferedReader(new FileReader(file)).lines().forEach(this::command);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        Puzzle1 p = new Puzzle1();
        p.process(args[0]);
    }

}
