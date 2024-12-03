import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) throws Exception {
        int[][] arrs = readFile("input/day1");
        sortArrays(arrs);
        int[] arr1 = arrs[0];
        int[] arr2 = arrs[1];

        int totalDistance = historianHysteriaPart1(arr1, arr2);
        int similarityScore = historianHysteriaPart2(arr1, arr2);

        System.out.println(totalDistance);
        System.out.println(similarityScore);
    }

    public static int historianHysteriaPart1(int[] arr1, int[] arr2) {
        int result = 0;

        for (int j = 0; j < arr1.length; j++) {
            result += Math.abs(arr1[j] - arr2[j]);
        }

        return result;
    }

    public static int historianHysteriaPart2(int[] arr1, int[] arr2) {
        int result = 0;
        int currentCount = 0;
        int j = 0;

        for (int i = 0; i < arr1.length; i++) {
            if (i == 0 || arr1[i] != arr1[i - 1]) {
                currentCount = 0;
                while (j < arr2.length && arr2[j] <= arr1[i]) {
                    if (arr2[j] == arr1[i]) {
                        currentCount++;
                    }
                    j++;
                }
            }
            result += arr1[i] * currentCount;
        }

        return(result);
    }

    public static int[][] readFile(String fileName) throws FileNotFoundException {
        int length = 0;
        File file = new File(fileName);
        Scanner sc1 = new Scanner(file);

        while (sc1.hasNextLine()) {
            length++;
            sc1.nextLine();
        }

        int[] arr1 = new int[length];
        int[] arr2 = new int[length];

        Scanner sc2 = new Scanner(file);

        int i = 0;

        while (sc2.hasNextLine()) {
            String line = sc2.nextLine();
            arr1[i] = Integer.parseInt((line.split("( )+")[0]));
            arr2[i] = Integer.parseInt((line.split("( )+")[1]));
            i++;
        }
        return new int[][]{arr1, arr2};
    }

    public static void sortArrays(int[][] arrs) {
        for (int[] arr : arrs) {
            Arrays.sort(arr);
        }
    }
}