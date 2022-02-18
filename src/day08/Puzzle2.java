package day08;

import java.util.ArrayList;

/**
 *  Seven Segment Search part 2.
 */
class Puzzle2 extends Puzzle1 {

    /**
     * Converts a signal string into a bit pattern (a->1, b->2, c->4, ...).
     */
    int toBits(String s) {
        int i = 0;
        
        for (char c: s.toCharArray()) {
            i = i | (1 << (c - 'a'));
        }
        
        return i;
    }

    /**
     * Converts a bit pattern into a signal string (1->a, 2->b 4->c, ...).
     */
    String toChars(int i) {
        StringBuilder s = new StringBuilder();
        
        char c = 'a';
        while (i != 0) {
            if ((i & 1) == 1) {
                s.append(c);
            }
            c = (char)(c + 1);
            i = i >> 1;
        }
        
        return s.toString();
    }

    /**
     * Finds in a list of signal combinations the one that contains "length"
     * signals and is a proper superset of "include" (i.e. contains it fully).
     */
    int find(ArrayList<Integer> wires, int length, int include) {
        for (int i = wires.size() - 1; i >= 0; i--) {
            int s = wires.get(i);
            
            if (Integer.bitCount(s) != length) continue;                
            if ((s & include) != include) continue;
            
            wires.remove(i);
            System.out.println(toChars(i));
            
            return s;
        }
        
        throw new RuntimeException("Oops!");
    }
    
    /**
     * Solves one line of input.
     */
    void analyze(ArrayList<Integer> wires, int[] numbers) {
        int[] digits = new int[10];

        digits[1] = find(wires, 2, 0);
        digits[4] = find(wires, 4, 0);
        digits[7] = find(wires, 3, 0);
        digits[8] = find(wires, 7, 0);

        digits[3] = find(wires, 5, digits[1]);
        digits[9] = find(wires, 6, digits[4]);
        digits[0] = find(wires, 6, digits[1]);
        digits[6] = find(wires, 6, 0);
        
        digits[2] = find(wires, 5, digits[8] ^ digits[6]);   

        digits[5] = find(wires, 5, 0);   
        
        for (int i = 0; i < 10; i++) {
            System.out.println(i + " -> " + toChars(digits[i]));
        }
        
        int n = 0;
        
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < 10; j++) {
                if (numbers[i] == digits[j]) {
                    n = n * 10 + j;
                    System.out.println(toChars(numbers[i]) + " = " + j);
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
                
        ArrayList<Integer> wires = new ArrayList();
        for (String s: c) {
            wires.add(toBits(s));
        }
        
        int[] digits = new int[4];
        for (int i = 0; i < 4; i++) {
            digits[i] = toBits(b[i]);
        }
        
        analyze(wires, digits);

        System.out.println();
    }
    
    public static void main(String[] args) {
        new Puzzle2().process(args[0]);
    }
    
}
