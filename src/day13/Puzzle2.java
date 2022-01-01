package day13;

/**
 * Day 13 "Transparent Origami" part 2.
 */
public class Puzzle2 extends Puzzle1 {

    int xMax = Integer.MIN_VALUE;
    int yMax = Integer.MIN_VALUE;
    
    @Override    
    void plot(Point p) {
        int x = p.x();
        int y = p.y();
        
        for (Point t: folds) {
            x = x > t.x() ? 2 * t.x() - x : x;
            y = y > t.y() ? 2 * t.y() - y : y;
        }
        
        xMax = Math.max(xMax, x);
        yMax = Math.max(yMax, y);

        Point q = new Point(x, y);
        output.add(q);
        
        System.out.println(p + " -> " + q);
    }
    
    @Override
    void dump() {
        for (int i = 0; i <= yMax; i++) {
            for (int j = 0; j <= xMax; j++) {
                if (output.contains(new Point(j ,i))) {
                    System.out.print('\u2588');
                } else {
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        new Puzzle2().process(args[0]);
    }
    
}
