package day02;

/**
 * Day 2 "Dive" part 2.
 */
public class Puzzle2 extends Puzzle1 {
    
    int aim;

    @Override
    void command(String s) {
        int p = s.indexOf(' ');

        char cmd = s.charAt(0);
        int arg = Integer.parseInt(s.substring(p + 1));

        switch (cmd) {
            case 'u' -> aim = aim - arg;
            case 'd' -> aim = aim + arg;
            case 'f' -> {
                length = length + arg;
                depth = depth + aim * arg;
            }
        }

        System.out.println(depth * length);
    }

    public static void main(String[] args) {
        Puzzle2 p = new Puzzle2();
        p.process(args[0]);
    }

}
