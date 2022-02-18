package day08;

import java.util.ArrayList;

/**
 *  Seven Segment Search part 2. Poor man's set processing based on strings.
 */
class Puzzle2 extends Puzzle1 {
    
    /**
     * Returns "s minus t".
     */
    String diff(String s, String t) {
        String result = "";
        
        for (char c: s.toCharArray()) {
            if (t.indexOf(c) == -1) {
                result += c;
            }
        }
        
        return result;
    }

    /**
     * Returns "s equals t".
     */
    boolean same(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        
        for (char c: s.toCharArray()) {
            if (t.indexOf(c) == -1) {
                return false;
            }
        }
        
        return true;
    }

    /**
     * Finds in a list of signal combinations the one that contains "length"
     * signals, has all "include" signals and no "exlude" signal.
     */
    String find(ArrayList<String> wires, int length, String include, String exclude) {
        for (int i = wires.size() - 1; i >= 0; i--) {
            String s = wires.get(i);
            
            if (s.length() != length) continue;                
            if (diff(include, s).length() != 0) continue;
            if (diff(exclude, s).length() < exclude.length()) continue;
            
            wires.remove(i);
            System.out.println(s);
            
            return s;
        }
        
        return null;
    }
    
    /**
     * Solves one line of input.
     */
    void analyze(ArrayList<String> wires, String[] numbers) {
        String[] digits = new String[10];

        digits[1] = find(wires, 2, "", "");
        digits[4] = find(wires, 4, "", "");
        digits[7] = find(wires, 3, "", "");
        digits[8] = find(wires, 7, "", "");

        digits[3] = find(wires, 5, digits[1], "");
        digits[9] = find(wires, 6, digits[4], "");
        digits[0] = find(wires, 6, digits[1], "");
        digits[6] = find(wires, 6, "", "");
        
        digits[2] = find(wires, 5, diff(digits[8], digits[6]), "");   

        digits[5] = find(wires, 5, "", "");   
        
        for (int i = 0; i < 10; i++) {
            System.out.println(i + " -> " + digits[i]);
        }
        
        int n = 0;
        
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < 10; j++) {
                if (same(numbers[i], digits[j])) {
                    n = n * 10 + j;
                    System.out.println(numbers[i] + " = " + j);
                }
            }
        }
        
        System.out.println(n);
        
        count += n;
    }
     
    @Override
    void processLine(String line) {
        String[] a = line.split("\\s\\|\\s"); // Separate left/right part
        String[] b = a[1].trim().split(" ");  // Separate the four digits
        String[] c = a[0].trim().split(" ");  // Separate signal patterns
                
        ArrayList<String> wires = new ArrayList();
        for (String s: c) {
            wires.add(s);
        }
        analyze(wires, b);

        System.out.println();
    }
    
    public static void main(String[] args) {
        new Puzzle2().process(args[0]);
    }
    
}
