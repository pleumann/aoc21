package day04;

/**
 * Day 4 "Giant Squid" part 2.
 */
public class Puzzle2 extends Puzzle1 {
    
    @Override
    void update(int round, int score) {
        if (resultRound == 0 || round > resultRound) {
            resultRound = round;
            resultScore = score;
        }
    }
    
    public static void main(String[] args) {
        Puzzle2 p = new Puzzle2();
        p.process(args[0]);
    }
    
}
