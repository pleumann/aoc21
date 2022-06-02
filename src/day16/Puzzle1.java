package day16;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Puzzle1 {

    int versions;
    
    int parseBin(String s, int i) {
        int version = Integer.parseInt(s.substring(i, i + 3), 2);
        i += 3;
        
        System.out.println("Version : " + version);
        
        versions = versions + version;
        
        int type = Integer.parseInt(s.substring(i, i + 3), 2);
        i += 3;
        
        System.out.println("Type    : " + type);
        
        if (type == 4) {
            String t = s.substring(i, i + 5);
            i += 5;
            
            long literal = Integer.parseInt(t.substring(1), 2);
            
            while (t.startsWith("1")) {
                t = s.substring(i, i + 5);
                i += 5;
                
                literal = (literal << 4) | Integer.parseInt(t.substring(1), 2);
            }
            
            System.out.println("Literal: " + literal);
        } else {
            char c = s.charAt(i);
            i++;
            
            if (c == '0') {
                int length = Integer.parseInt(s, i, i + 15, 2);
                System.out.println("Length : " + length);
                
                i += 15;
                
                int end = i + length;
                while (i < end) {
                    i = parseBin(s, i);
                }
            } else {
                int count = Integer.parseInt(s, i, i + 11, 2);
                System.out.println("Count  : " + count);
                
                i += 11;
                
                while (count > 0) {
                    i = parseBin(s, i);
                    count--;
                }
            }
        }
        
        return i;
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
        
        parseBin(t, 0);
        
        System.out.println("Sum of versions: " + versions);
        
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
        new Puzzle1().process(args[0]);
    }
    
}
