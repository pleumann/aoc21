package day01;

/**
 * Day 1 "Sonar Sweep" part 2.
 */
public class Puzzle2 extends Puzzle1 {

    int filter(int i) {
        return input[i - 2] + input[i - 1] + input[i];
    }
    
    @Override
    void checkReport() {
        int count = 0;
        
        for (int i = 3; i < input.length; i++) {
            if (filter(i) > filter(i - 1)) {
                count++;
            }
        }
        
        System.out.println(count);
    }

    public static void main(String[] args) {
        Puzzle2 p = new Puzzle2();
        p.loadInput(args[0]);
        p.checkReport();
    }

}
