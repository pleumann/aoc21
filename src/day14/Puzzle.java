package day14;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Day 14 "Extended Polimerization" parts 1 and 2.
 */
public class Puzzle {
   
    HashMap<String, Long> polymer = new HashMap<>();

    HashMap<String, String> rules = new HashMap<>();
    
    void add(HashMap<String, Long> set, String key, long value) {
        System.out.println("Adding " + value + " x " + key);
        
        long l = set.getOrDefault(key, Long.valueOf(0));
        set.put(key, l + value);
    }
    
    void process(String file, int steps) {
        try {
            System.out.println("----- Loading -----");
            System.out.println();
            
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String s = reader.readLine();
            reader.readLine();
            
            for (int j = 1; j < s.length(); j++) {
                 String from = s.substring(j - 1, j + 1);
                 add(polymer, from, 1);
            }
            
            s = reader.readLine();
            while (s != null) {
                rules.put(s.substring(0, 2), s.substring(6));
                s = reader.readLine();
            }
            
            for (int i = 1; i <= steps; i++) {
                System.out.println();
                System.out.println("----- Step " + i + " -----");
                System.out.println();
                
                HashMap<String, Long> output = new HashMap<>();
                
                for (String key: polymer.keySet()) {
                    String t = rules.get(key);
                    String from = key.substring(0, 1) + t;
                    String to = t + key.substring(1);
                    
                    add(output, from, polymer.get(key));
                    add(output, to, polymer.get(key));
                }
                
                polymer = output;
            }

            System.out.println();
            System.out.println("----- Result -----");
            System.out.println();

            long[] count = new long[26];
            for (String key: polymer.keySet()) {
                int idx = key.charAt(1) - 'A';
                count[idx] += polymer.get(key);
            }

            long min = Long.MAX_VALUE;
            long max = Long.MIN_VALUE;
            
            for (char c = 'A'; c <= 'Z'; c++) {
                long l = count[c - 'A'];
                System.out.println(c + " " + l);
                
                if (l != 0) {
                    min = Long.min(l, min);
                    max = Long.max(l, max);
                }
            }
            
            System.out.println();
            System.out.println(max - min);
            
        } catch (IOException e) {
            throw new RuntimeException("Error reading input", e);
        }   
    }
    
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java ... <input> <steps>");
            System.exit(1);
        }
        
        new Puzzle().process(args[0], Integer.parseInt(args[1]));
    }
    
}
