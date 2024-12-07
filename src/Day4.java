import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day4 {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(ceresSearchPart1());
        System.out.println(ceresSearchPart2());
    }

    public static int ceresSearchPart1() throws FileNotFoundException {
        ArrayList<ArrayList<Character>> wordSearch = readFile("input/day4");
        int count = 0;
        int rowCount = wordSearch.size();
        int rowSize = wordSearch.getFirst().size();

        for (int i = 0; i < rowCount; i++) {
            ArrayList<Character> currRow = wordSearch.get(i);
            for (int j = 0; j < rowSize; j++) {
                if (currRow.get(j) == 'X') {
                    count = getXmasCount(count, rowSize, j, currRow, currRow, currRow);

                    if (i < rowCount - 3) {
                        count = getXmasCount(count, rowSize, j, wordSearch.get(i + 1), wordSearch.get(i + 2), wordSearch.get(i + 3));
                    }

                    if (i > 2) {
                        count = getXmasCount(count, rowSize, j, wordSearch.get(i - 1), wordSearch.get(i - 2), wordSearch.get(i - 3));
                    }
                }
            }
        }

        return count;
    }

    public static int ceresSearchPart2() throws FileNotFoundException {
        ArrayList<ArrayList<Character>> wordSearch = readFile("input/day4");
        int count = 0;
        int rowCount = wordSearch.size();
        int rowSize = wordSearch.getFirst().size();

        for (int i = 1; i < rowCount - 1; i++) {
            ArrayList<Character> currRow = wordSearch.get(i);
            ArrayList<Character> prevRow = wordSearch.get(i - 1);
            ArrayList<Character> nextRow = wordSearch.get(i + 1);
            for (int j = 1; j < rowSize - 1; j++) {
                if (currRow.get(j) == 'A') {
                    Character topLeft = prevRow.get(j - 1);
                    Character topRight = prevRow.get(j + 1);
                    Character bottomLeft = nextRow.get(j - 1);
                    Character bottomRight = nextRow.get(j + 1);

                    if (topLeft == 'M' && bottomRight == 'S' && topRight == 'M' && bottomLeft == 'S') {
                        count++;
                    } else if (topLeft == 'M' && bottomRight == 'S' && topRight == 'S' && bottomLeft == 'M') {
                        count++;
                    } else if (topLeft == 'S' && bottomRight == 'M' && topRight == 'S' && bottomLeft == 'M') {
                        count++;
                    } else if (topLeft == 'S' && bottomRight == 'M' && topRight == 'M' && bottomLeft == 'S') {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    private static int getXmasCount(int count, int rowSize, int j, ArrayList<Character> adjRow1, ArrayList<Character> adjRow2, ArrayList<Character> adjRow3) {
        if (j < rowSize - 3 && adjRow1.get(j + 1) == 'M' && adjRow2.get(j + 2) == 'A' && adjRow3.get(j + 3) == 'S') {
            count++;
        }
        if (j > 2 && adjRow1.get(j - 1) == 'M' && adjRow2.get(j - 2) == 'A' && adjRow3.get(j - 3) == 'S') {
            count++;
        }
        if (adjRow1.get(j) == 'M' && adjRow2.get(j) == 'A' && adjRow3.get(j) == 'S') {
            count++;
        }
        return count;
    }

    public static ArrayList<ArrayList<Character>> readFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner sc = new Scanner(file);
        ArrayList<ArrayList<Character>> wordSearch = new ArrayList<>();

        while (sc.hasNextLine()) {
            String rowString = sc.nextLine();
            ArrayList<Character> row = new ArrayList<>();

            for (int i = 0; i < rowString.length(); i++) {
                row.add(rowString.charAt(i));
            }

            wordSearch.add(row);
        }

        return wordSearch;
    }
}
