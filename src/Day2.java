import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) throws Exception {
        ArrayList<ArrayList<Integer>> arrs = readFile("input/day2");

        System.out.println(arrs.size() - redNosedReportsPart1(arrs).size());
        System.out.println(arrs.size() - redNosedReportsPart2(arrs));
    }

    public static boolean isReportSafe(ArrayList<Integer> arr) {
        int i = 0;
        boolean safe = true;
        int order = arr.get(0) < arr.get(1) ? -1 : 1;

        while (safe && i < arr.size() - 1) {
            int diff = (arr.get(i) - arr.get(i + 1)) * order;
            if (diff < 1 || diff > 3) {
                safe = false;
            }
            i++;
        }

        return safe;
    }

    public static ArrayList<ArrayList<Integer>> redNosedReportsPart1(ArrayList<ArrayList<Integer>> reports) {
        ArrayList<ArrayList<Integer>> unsafeReports = new ArrayList<>();

        for (ArrayList<Integer> report : reports) {
            boolean safe = isReportSafe(report);

            if (!safe) {
                unsafeReports.add(report);
            }
        }

        return unsafeReports;
    }

    public static int redNosedReportsPart2(ArrayList<ArrayList<Integer>> reports) {
        ArrayList<ArrayList<Integer>> unsafeReports = redNosedReportsPart1(reports);
        int unsafeReportsCount = unsafeReports.size();

        for (ArrayList<Integer> report : unsafeReports) {
            boolean safe = false;
            int i = 0;
            while (!safe && i < report.size()) {
                ArrayList<Integer> arrCopy = new ArrayList<>(report);
                arrCopy.remove(i);
                safe = isReportSafe(arrCopy);
                i++;
            }
            if (safe) {
                unsafeReportsCount--;
            }
        }

        return unsafeReportsCount;
    }

    public static ArrayList<ArrayList<Integer>> readFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        ArrayList<ArrayList<Integer>> reports = new ArrayList<ArrayList<Integer>>();
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] levelStrings = line.split("( )+");
            ArrayList<Integer> levels = new ArrayList<Integer>();
            for (String levelString : levelStrings) {
                levels.add(Integer.parseInt(levelString));
            }
            reports.add(levels);
        }

        return reports;
    }
}
