package day20;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

/**
 * Day 20 "Trench Map" part 1.
 */
public class Puzzle1 {
    
    /**
     * Represents an image. Only "lit" pixels are stored. We keep track of the
     * rectangle that we actually "calculated". Everything else has a default
     * value (because the pixel is supposed to be infinite).
     */
    class Image {
        /**
         * The list pixels. Hash key is a string like "3,5" or "-7,100".
         */
        HashSet<String> pixels = new HashSet();

        /**
         * The rectangle in which there are "lit" pixels.
         */
        int x1, x2, y1, y2;
        
        /**
         * The default value for the rest of the infinite image.
         */
        boolean def;
        
        /**
         * Creates a new, empty image. The initial rectagle is empty. The
         * coordinates are chosen so that they can be updated easily by min/max.
         */
        Image() {
            x1 = Integer.MAX_VALUE;
            y1 = Integer.MAX_VALUE;
            x2 = Integer.MIN_VALUE;
            y2 = Integer.MIN_VALUE;
        }
            
        /**
         * Returns the value of a pixel.
         */
        boolean get(int x, int y) {
            if (x < x1 || y < y1 || x > x2 || y > y2) {
                return def;
            }

            return pixels.contains("" + x + "/" + y);
        }
        
        /**
         * Light a pixel.
         */
        void set(int x, int y) {
            pixels.add("" + x + "/" + y);
            
            x1 = Math.min(x1, x);
            y1 = Math.min(y1, y);
            x2 = Math.max(x2, x);
            y2 = Math.max(y2, y);
        }
        
        /**
         * Returns a printable string representing the image.
         */
        @Override
        public String toString() {
            return String.format("x1=%d, y1=%d, x2=%d, y2=%d, def=%b, pixels=%d", x1, y1, x2, y2, def, pixels.size());
        }
        
    }
    
    /**
     * The algorithm in a single line.
     */
    String algorithm = "";

    /**
     * The "current" image.
     */
    Image image = new Image();

    /**
     * Applies one round of improvement.
     */
    public void improve() {
        Image temp = new Image();
    
        for (int x = image.x1 - 1; x < image.x2 + 2; x++) {
            for (int y = image.y1 - 1; y < image.y2 + 2; y++) {
                int index = 0;

                for (int i = - 1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        index = (index << 1) | (image.get(x + i, y + j) ? 1 : 0);
                    }
                }

                if (algorithm.charAt(index) == '#') {
                    temp.set(x, y);
                }
                
            }
        }
        
        temp.def = algorithm.charAt(image.def ? 511 : 0) == '#';
        
        image = temp;
    }
    
    /**
     * Processes the input coming from the given file.
     */
    public void process(String file, int rounds) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s = br.readLine();
        
        while (!"".equals(s)) {
            algorithm = algorithm + s;
            s = br.readLine();
        }

        s = br.readLine();
    
        int row = 0;
        while (s != null) {
            System.out.println(s);
            
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '#') {
                    image.set(row, i);
                }
            }
            row++;
            s = br.readLine();
        }

        System.out.println("Starting image: " + image);
        
        for (int n = 1; n <= rounds; n++) {
            improve();
            System.out.println("Round #" + n + " image: " + image);
        }        
    }
    
    /**
     * Entry point.
     */
    public static void main(String[] args) {
        try {        
            new Puzzle1().process(args[0], 2);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
