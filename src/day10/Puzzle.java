package day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * Syntax Scoring parts 1 and 2.
 */
public class Puzzle {

    /**
     * A stack for open navigation chunks.
     */
    Stack<Character> stack = new Stack();
    
    /**
     * Parses a single line, returns score. Score is zero for well-formed lines,
     * positive for incomplete ones and negative for errors.
     */
    long parse(String s) {
        stack.clear();
        
        for (char c: s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{' || c == '<') {
                stack.push(c);
            } else {
                char d = stack.pop();

                // The following line works because matching parentheses are no
                // more than 2 positions away from each other in ASCII/Unicode.
                if (Math.abs(c - d) > 2) {
                    System.out.println(s + " Expected '" + d + "' but got '" + c + "' instead.");

                    switch (c) {
                        case ')': return -3;
                        case ']': return -57;
                        case '}': return -1197;
                        case '>': return -25137;
                    }
                }
            }
        }
        
        long score = 0;
        while (!stack.empty()) {
            switch (stack.pop()) {
                case '(': { score = score * 5 + 1; break; }
                case '[': { score = score * 5 + 2; break; }
                case '{': { score = score * 5 + 3; break; }
                case '<': { score = score * 5 + 4; break; }
            }
        }
        
        if (score != 0) {
            System.out.println(s + " Incomplete line, score is " + score + ".");
        }
        
        return score;
    }
    
    /**
     * Processes the whole input file, line by line.
     */
    void process(String file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            long error = 0;
            ArrayList<Long> missing = new ArrayList<>();
            
            String line = reader.readLine(); 
            while (line != null) {
                long score = parse(line);
                
                if (score < 0) {
                    error = error - score;
                } else if (score > 0) {
                    missing.add(score);
                }
                
                line = reader.readLine(); 
            }
            
            Collections.sort(missing);
            
            System.out.println();
            System.out.println("Syntax error score: " + error);
            System.out.println("Autocomplete score:   " + missing.get(missing.size() / 2));
            System.out.println();
            
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        
    }
    
    public static void main(String[] args) {
        new Puzzle().process(args[0]);
    }
    
}
