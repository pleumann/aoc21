package day07;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Util {

    public static int[] readSingleLineIntArray(String file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
         
            String[] strings = reader.readLine().split(",");

            int length = strings.length;
            
            int[] values = new int[length];
            
            for (int i = 0; i < length; i++) {
                values[i] = Integer.parseInt(strings[i]);
            }
            
            return values;
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
}
