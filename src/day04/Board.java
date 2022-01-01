package day04;

/**
 * Represents a Bingo board. Is able to check numbers that are being called.
 */
public class Board {

    /**
     * Numbers on this board.
     */
    int[][] numbers = new int[5][5];
    
    /**
     * Called numbers per row.
     */
    int[] rowCount = new int[5];
    
    /**
     * Called numbers per column.
     */
    int[] colCount = new int[5];

    /**
     * Total value of the board.
     */
    int value;
    
    /**
     * Creates a board from the input strings.
     */
    public Board(String ... input) {
        for (int i = 0; i < 5; i++) {
            System.out.println(input[i]);
            String[] s = input[i].trim().split("\\s+");
            for (int j = 0; j < 5; j++) {
                int k = Integer.parseInt(s[j]);
                numbers[i][j] = k;
                value += k;
            }
        }
    }
    
    /**
     * Calls a number. Returns score if board is finished or -1 if not.
     */
    public int call(int number) {
        System.out.println("*" + number + "*");
        
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (numbers[i][j] == number) {
                    rowCount[i]++;
                    colCount[j]++;
                    value = value - number;
                    if (rowCount[i] == 5 || colCount[j] == 5) {
                        System.out.println("Bingo!");
                        return number * value;
                    }
                }
            }
        }
        
        return -1;
    }
    
}
