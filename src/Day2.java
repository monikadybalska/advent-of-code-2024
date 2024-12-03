import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) throws Exception {
        ArrayList<ArrayList<Integer>> arrs = readFile("input/day2");

        System.out.println(arrs.size() - redNosedReportsPart1(arrs).size());
        System.out.println(redNosedReportsPart2(arrs));
    }

    public static ArrayList<ArrayList<Integer>> redNosedReportsPart1(ArrayList<ArrayList<Integer>> reports) {
        ArrayList<ArrayList<Integer>> unsafeReports = new ArrayList<>();

        for (ArrayList<Integer> report : reports) {
            boolean safe = true;
            int i = 0;
            String order = getOrder(report.get(0), report.get(1));

            while (safe && i < report.size() - 1) {
                int curr = report.get(i);
                int next = report.get(i + 1);
                String currOrder = getOrder(curr, next);
                int diff = Math.abs(curr - next);
                if (!order.equals(currOrder) || diff < 1 || diff > 3) {
                    safe = false;
                }
                i++;
            }

            if (!safe) {
                unsafeReports.add(report);
            }
        }

        return unsafeReports;
    }

    public static int redNosedReportsPart2(ArrayList<ArrayList<Integer>> reports) {
        int safeReportsCount = 0;

        for (ArrayList<Integer> report : reports) {
            int unsafeLevelsCount = 0;
            int i = 1;
            int curr = report.getFirst();
            int next = report.get(i);
            String order = getOrder(curr, next);

            while (unsafeLevelsCount <= 1 && i < report.size()) {
                next = report.get(i);
                int diff = Math.abs(curr - next);
                String currOrder = getOrder(curr, next);
                if (!order.equals(currOrder) || diff < 1 || diff > 3) {
                    if (i > 1) {
                        i++;
                    } else {
                        curr = next;
                        currOrder = getOrder(curr, report.get(i+1));
                    }
                    unsafeLevelsCount++;
                } else {
                    curr = next;
                }
                order = currOrder;
                i++;
            }

            if (unsafeLevelsCount <= 1) {
                safeReportsCount++;
            }
        }

        return safeReportsCount;
    }

    public static int redNosedReportsPart2V2(ArrayList<ArrayList<Integer>> reports) {
        ArrayList<ArrayList<Integer>> unsafeReports = redNosedReportsPart1(reports);
        int unsafeReportsWithDampener = unsafeReports.size();

        for (ArrayList<Integer> report : unsafeReports) {
            boolean safe = false;

            for (int levelId = 0; levelId < report.size(); levelId++) {
                safe = true;
                ArrayList<Integer> arrCopy = new ArrayList<>(report);
                arrCopy.remove(levelId);
                int i = 0;
                String order = getOrder(arrCopy.get(0), arrCopy.get(1));
                while (safe && i < arrCopy.size() - 1) {
                    int curr = arrCopy.get(i);
                    int next = arrCopy.get(i + 1);
                    String currOrder = getOrder(curr, next);
                    int diff = Math.abs(curr - next);
                    if (!order.equals(currOrder) || diff < 1 || diff > 3) {
                        safe = false;
                    }
                    i++;
                }
                if (safe) {
                    unsafeReportsWithDampener--;
                    break;
                }
            }
        }

        return unsafeReportsWithDampener;
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

    public static String getOrder(int curr, int next) {
        if (curr > next) {
            return "desc";
        } else if (curr < next) {
            return "asc";
        } else {
            return "equal";
        }
    }
}
