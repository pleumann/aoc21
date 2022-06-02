package day16;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Puzzle3 {

    public static final String[] CHUNKS = {"ADD", "MUL", "MIN", "MAX", "LIT", "LT", "GT", "EQ"};

    String message;
    
    int index;
    
    int versions;
    
    int parseNumber(int bits) {
        index += bits;
        return Integer.parseInt(message, index - bits, index, 2);
    }
    
    long parsePacket(String prefix) {
        int v = parseNumber(3);
        int type = parseNumber(3);
        
        long value;
        
        System.out.println(prefix + CHUNKS[type] + " v" + v + " {");

        versions = versions + v;
        
        if (type == 4) {
            boolean more = parseNumber(1) == 1;
            value = parseNumber(4);
            
            while (more) {
                more = parseNumber(1) == 1;
                value = (value << 4) | parseNumber(4);
            }
        } else {
            boolean mode = parseNumber(1) == 1;

            int end = mode ? Integer.MAX_VALUE : parseNumber(15) + index;
            int count = mode ? parseNumber(11) : Integer.MAX_VALUE;
            
            ArrayList<Long> subs = new ArrayList<>();
            while (index < end && count-- > 0) {
                subs.add(parsePacket(prefix + "  "));
            }
            
            value = switch (type) {
                case 0 -> subs.stream().reduce(0L, (a, b) -> a + b);
                case 1 -> subs.stream().reduce(1L, (a, b) -> a * b);
                case 2 -> subs.stream().min(Long::compareTo).get();
                case 3 -> subs.stream().max(Long::compareTo).get();
                case 5 -> subs.get(0) > subs.get(1) ? 1 : 0;
                case 6 -> subs.get(0) < subs.get(1) ? 1 : 0;
                case 7 -> subs.get(0).equals(subs.get(1)) ? 1 : 0;
                default -> -1; // Can't happen
            };
        }
        
        System.out.println(prefix + "} = " + value);

        return value;
    }
    
    void parseMessage(String s) {
        StringBuilder sb = new StringBuilder(s.length() * 4);
        
        for (char c: s.toCharArray()) {
            String t = Integer.toBinaryString(Character.digit(c, 16));
            sb.append("0000".substring(t.length()));
            sb.append(t);
        }
        
        message = sb.toString();
        versions = 0;
        index = 0;
        
        long result = parsePacket("");
        
        System.out.println("Sum of versions: " + versions);
        System.out.println("Overall result : " + result);
    }
    
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            br.lines().forEach(s -> new Puzzle3().parseMessage(s));
        } catch (IOException e) {
            throw new RuntimeException("Error reading input", e);
        }   
    }
    
}
