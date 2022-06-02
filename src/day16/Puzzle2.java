package day16;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Puzzle2 {

    public static final String[] CHUNKS = {"ADD", "MUL", "MIN", "MAX", "LIT", "LT", "GT", "EQ"};
    
    int index;
    
    int versions;
    
    long parseBin(String prefix, String s) {
        int version = Integer.parseInt(s.substring(index, index + 3), 2);
        index += 3;
        
        System.out.println(prefix + "Version: " + version);
        
        versions = versions + version;
        
        int type = Integer.parseInt(s.substring(index, index + 3), 2);
        index += 3;
        
        System.out.println(prefix + "Type   : " + type + " (" + CHUNKS[type] + ")");
        
        if (type == 4) {
            String t = s.substring(index, index + 5);
            index += 5;
            
            long literal = Long.parseLong(t.substring(1), 2);
            
            while (t.startsWith("1")) {
                t = s.substring(index, index + 5);
                index += 5;
                
                literal = (literal << 4) | Long.parseLong(t.substring(1), 2);
            }
            
            System.out.println(prefix + "Literal: " + literal);
            
            return literal;
        } else {
            char c = s.charAt(index);
            index++;

            int end = Integer.MAX_VALUE;
            int count = Integer.MAX_VALUE;
            
            if (c == '0') {
                int length = Integer.parseInt(s, index, index + 15, 2);
                System.out.println(prefix + "Length : " + length);
                index += 15;
                end = index + length;
            } else {
                count = Integer.parseInt(s, index, index + 11, 2);
                System.out.println(prefix + "Count  : " + count);
                index += 11;
            }
            
            if (count == 0 || end == index) {
                throw new RuntimeException("Nasty");
            }
            
            ArrayList<Long> subs = new ArrayList<>();
        
            while (index < end && count > 0) {
                if (index + 6 >= end) {
                    throw new RuntimeException("Nasty");
                }
                
                System.out.println(prefix + "(");
                long l = parseBin(prefix + "  ", s);
                System.out.println(prefix + ") = " + l);
                subs.add(l);
                count--;
            }
            
            if (type > 4 && subs.size() != 2) {
                throw new RuntimeException("Nasty");
            }
            
            switch (type) {
                case 0: {
                    long result = 0;
                    for (Long l: subs) {
                        result = result + l;
                    }
                    return result;
                }
                case 1: {
                    long result = 1;
                    for (Long l: subs) {
                        result = result * l;
                    }
                    return result;
                }
                case 2: {
                    long result = Long.MAX_VALUE;
                    for (Long l: subs) {
                        result = Math.min(result, l);
                    }
                    return result;
                }
                case 3: {
                    long result = 0;
                    for (Long l: subs) {
                        result = Math.max(result, l);
                    }
                    return result;
                }
                case 5: {
                    return subs.get(0) > subs.get(1) ? 1 : 0;
                }
                case 6: {
                    return subs.get(0) < subs.get(1) ? 1 : 0;
                }
                case 7: {
                    return subs.get(0).equals(subs.get(1)) ? 1 : 0;
                }
                default: {
                    throw new RuntimeException("Oops!");
                }
            }
            
        }
    }
    
    void parseHex(String s) {
        System.out.println("Hex: " + s);
        
        StringBuilder sb = new StringBuilder(s.length() * 4);
        
        for (int i = 0; i < s.length(); i++) {
            String t = Integer.toBinaryString(Integer.parseInt(s, i, i + 1, 16));
            sb.append("0000".substring(t.length()));
            sb.append(t);
        }
        
        String t = sb.toString();
        
        System.out.println("Bin: " + t);
        
        versions = 0;
        index = 0;
        
        long result = parseBin("", t);
        
        System.out.println("-------------------------------------------------");
        System.out.println("Overall result : " + result);
        System.out.println("Sum of versions: " + versions);
        System.out.println("Remaining bits : " + (t.length() - index));
        System.out.println("-------------------------------------------------");
    }
    
    void process(String file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            reader.lines().forEach(this::parseHex);
            
        } catch (IOException e) {
            throw new RuntimeException("Error reading input", e);
        }   
    }
    
    public static void main(String[] args) {
        new Puzzle2().process(args[0]);
    }
    
}
