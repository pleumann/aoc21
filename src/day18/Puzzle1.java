package day18;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Day 18 "Snailfish" part 1.
 */
public class Puzzle1 {
    
    StringBuilder number;
    
    /**
     * Performs explosion, if possible, and returns true to indicate that.
     * 
     * "To explode a pair, the pair's left value is added to the first regular
     * number to the left of the exploding pair (if any), and the pair's right
     * value is added to the first regular number to the right of the exploding
     * pair (if any). Exploding pairs will always consist of two regular
     * numbers. Then, the entire exploding pair is replaced with the regular
     * number 0."
     */
    boolean explode() {
        int level = 0;
        for (int i = 0; i < number.length(); i++) {
            char c = number.charAt(i);
            if (c == '[') {
                level++;
                
                if (Character.isDigit(number.charAt(i + 1))) {
                    if (level > 4) {
                        // Find the two elements belonging to this pair.
                        int j = number.indexOf(",", i + 1);
                        int k = number.indexOf("]", j + 1);

                        int a;
                        int b;

                        // Try to convert to int, exception if not a number
                        try {
                            a = Integer.parseInt(number, i + 1, j, 10);
                            b = Integer.parseInt(number, j + 1, k, 10);
                        } catch (Exception e) {
                            continue;
                        }

                        // Find first regular number to the right and add b
                        int l = k + 1;
                        while (l < number.length() && !Character.isDigit(number.charAt(l))) {
                            l++;
                        }

                        int m = l;
                        while (m < number.length() && Character.isDigit(number.charAt(m))) {
                            m++;
                        }

                        if (l < number.length()) {
                            number.replace(l, m, "" + (b + Integer.parseInt(number, l, m, 10)));
                        }

                        // Replace old pair by 0
                        number.replace(i, k + 1, "0");

                        // Find first regular number to the left and add a
                        m = i;
                        while (m > 0 && !Character.isDigit(number.charAt(m - 1))) {
                            m--;
                        }

                        l = m - 1;
                        while (l > 0 && Character.isDigit(number.charAt(l - 1))) {
                            l--;
                        }

                        if (l >= 0) {
                            number.replace(l, m, "" + (a + Integer.parseInt(number, l, m, 10)));
                        }

                        return true;
                    }
                }
            } else if (c == ']') {
                level --;
            }
        }
        
        return false;
    }
    
    /**
     * Performs a split, if necessary, and returns true to indicate that.
     * 
     * "To split a regular number, replace it with a pair; the left element of
     * the pair should be the regular number divided by two and rounded down,
     * while the right element of the pair should be the regular number divided
     * by two and rounded up."
     */
    boolean split() {
        for (int i = 0; i < number.length(); i++) {
            // Check if this is the start of a number
            if (Character.isDigit(number.charAt(i))) {
                int j = i + 1;
                while (j < number.length() && Character.isDigit(number.charAt(j))) {
                    j++;
                }
                
                // Two digits means we're >= 10 and need to split
                if (j > i + 1) {
                    int a = Integer.parseInt(number, i, j, 10);
                    int b = a / 2;
                    int c = a - b;
                    number.replace(i, j, "[" + b + "," + c + "]");
                    
                    return true;
                }
            }
        }
        
        return false;
    }
    
    /**
     * Reduce as long as explode or split are possible.
     * 
     * "During reduction, at most one action applies, after which the process
     * returns to the top of the list of actions. For example, if split produces
     * a pair that meets the explode criteria, that pair explodes before other
     * splits occur."
     */
    void reduce() {
        boolean again = true;
        
        while (again) {
            again = false;

            if (explode()) {
                System.out.println("Explode  : " + number.toString());
                again = true;
                continue;
            }

            if (split()) {
                System.out.println("Split    : " + number.toString());
                again = true;
            }
        }
        
    }

    /**
     * Calculates the magnitude of a snailfish number.
     * 
     * "The magnitude of a pair is 3 times the magnitude of its left element
     * plus 2 times the magnitude of its right element. The magnitude of a
     * regular number is just that number."
     */
    int magnitude() {
        String s = number.toString();

        // Regular expression for simple snailfish numbers (no substructure)
        Pattern p = Pattern.compile("\\[(\\d+)\\,(\\d+)\\]");
        
        boolean again = true;
        while (again) {
            again = false;
            
            // Find simple snailfish numbers and replace. Wash, rinse, repeat.
            Matcher m = p.matcher(s);
            if (m.find()) {
                int i = Integer.parseInt(m.group(1));
                int j = Integer.parseInt(m.group(2));
                s = s.substring(0, m.start()) + (3 * i + 2 * j) + s.substring(m.end());
                again = true;
            }
        }
        
        return Integer.parseInt(s);
    }    

    /**
     * Add the snailfish number to what we already have and reduce the result.
     */
    void process(String s) {
        if (number == null) { 
            number = new StringBuilder(s);
        } else {
            number = new StringBuilder("[" + number.toString() + "," + s + "]");
        }
        reduce();
    }
    
    public static void main(String[] args) {
        Puzzle1 p = new Puzzle1();
        try {        
            new BufferedReader(new FileReader(args[0])).lines().forEach(p::process);
            System.out.println("Magnitude: " + p.magnitude());
            System.out.println();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
