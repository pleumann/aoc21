package day24;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Day 24 "Arithmetic Logic Unit". This helper class translates a MONAD program
 * into Java code.
 */
public class Compiler {
    
    static int index;
    
    static void compile(String s) {
            String inst = s.substring(0, 3);
            char arg1 = s.charAt(4);
            String arg2 = s.length() > 6 ? s.substring(6) : "";
            
            switch (inst) {
                case "inp" -> {
                    if (index != 0) {
                        System.out.println("            }");
                    }
                    System.out.println("            case " + index + " -> {");
                    System.out.println("                " + arg1 + " = input;");
                    index++;
                }
                case "mul" -> {
                    System.out.println("                " + arg1 + " *= " + arg2 + ";");
                }
                case "add" -> {
                    System.out.println("                " + arg1 + " += " + arg2 + ";");
                }
                case "mod" -> {
                    System.out.println("                " + arg1 + " %= " + arg2 + ";");
                }
                case "div" -> {
                    System.out.println("                " + arg1 + " /= " + arg2 + ";");
                }
                case "eql" -> {
                    System.out.println("                " + arg1 + " = (" + arg1 + " == " + arg2 + ") ? 1 : 0;");
                }
            }
    }

    public static void main(String[] args) {
        
        int i = 0;
        
        System.out.println("""
            package day24;
                           
            /**                           
             * Day 24 \"Arithmetic Logic Unit\". Generated code.
             */
            class Monad {
            
                static int run(int index, int z, int input) {
                
                    int w = 0;
                    int x = 0;
                    int y = 0;
                    
                    switch (index) {""");
        
        try {
            new BufferedReader(new FileReader(args[0])).lines().forEach(Compiler::compile);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        
        System.out.println("""
                        }
                    }
                
                    return z;
                }
                           
            }""");
    }
    
}
