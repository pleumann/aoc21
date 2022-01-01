package day04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Day 4 "Giant Squid" part 1.
 */
public class Puzzle1 {
    
    int resultRound;
    
    int resultScore;
    
    void update(int round, int score) {
        if (resultRound == 0 || round < resultRound) {
            resultRound = round;
            resultScore = score;
        }
    }
    
    void process(String file) {
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String s = reader.readLine();
            
            String[] t = s.split(",");
            int[] numbers = new int[t.length];
            for (int i = 0; i < numbers.length; i++) {
                numbers[i] = Integer.parseInt(t[i]);
            }
            
            reader.readLine();
            
            s = reader.readLine();
            while (s != null) {
                System.out.println("--- Next board ---");
                Board b = new Board(s,
                                reader.readLine(),
                                reader.readLine(),
                                reader.readLine(),
                                reader.readLine());

                for (int i = 0; i < numbers.length; i++) {
                    int score = b.call(numbers[i]);
                    if (score != -1) {
                        System.out.println("Score: " + score);
                        update(i, score);
                        break;
                    }
               } 
                
               reader.readLine();
               
               s = reader.readLine();
            }
            
            System.out.println("Game ends in round " + resultRound + " with score " + resultScore + ".");
            
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
