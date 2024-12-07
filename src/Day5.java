import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day5 {
    public static void main(String[] args) throws FileNotFoundException {
        printQueuePart1();
        printQueuePart2();
    }

    public static void printQueuePart1() throws FileNotFoundException {
        ArrayList<ArrayList<ArrayList<Integer>>> data = readFile("input/day5");
        Map<Integer, Map<String, HashSet<Integer>>> pagesData = getPagesData(data.get(0));
        ArrayList<ArrayList<Integer>> pagesToProduce = data.get(1);
        ArrayList<ArrayList<Integer>> sortedPages = new ArrayList<>();

        for (ArrayList<Integer> page : pagesToProduce) {
            int i = 1;
            boolean isSorted = true;

            while (isSorted && i < page.size()) {
                HashSet<Integer> afters = pagesData.get(page.get(i)).get("after");

                for (int j = 0; j < i; j++) {
                    if (afters.contains(page.get(j))) {
                        isSorted = false;
                        break;
                    }
                }

                i++;
            }

            if (isSorted) {
                sortedPages.add(page);
            }
        }

        int sum = 0;
        for (ArrayList<Integer> page : sortedPages) {
            int middleIndex = (int) (double) (page.size() / 2);
            sum += page.get(middleIndex);
        }

        System.out.println(sum);
    }

    public static void printQueuePart2() throws FileNotFoundException {
        ArrayList<ArrayList<ArrayList<Integer>>> data = readFile("input/day5");
        Map<Integer, Map<String, HashSet<Integer>>> pagesData = getPagesData(data.get(0));
        ArrayList<ArrayList<Integer>> pagesToProduce = data.get(1);
        ArrayList<ArrayList<Integer>> unsortedPages = new ArrayList<>();

        for (ArrayList<Integer> page : pagesToProduce) {
            int i = 1;
            boolean isSorted = true;

            while (i < page.size()) {
                HashSet<Integer> afters = pagesData.get(page.get(i)).get("after");

                for (int j = 0; j < i; j++) {
                    int curr = page.get(j);
                    if (afters.contains(curr)) {
                        isSorted = false;
                        page.set(j, page.get(i));
                        page.set(i, curr);
                    }
                }

                i++;
            }

            if (!isSorted) {
                unsortedPages.add(page);
            }
        }

        int sum = 0;
        for (ArrayList<Integer> page : unsortedPages) {
            int middleIndex = (int) (double) (page.size() / 2);
            sum += page.get(middleIndex);
        }

        System.out.println(sum);
    }

    public static Map<Integer, Map<String, HashSet<Integer>>> getPagesData(ArrayList<ArrayList<Integer>> orderingRules) {
        Map<Integer, Map<String, HashSet<Integer>>> pages = new HashMap<>();

        for (ArrayList<Integer> rule : orderingRules) {
            for (Integer num : rule) {
                if (!pages.containsKey(num)) {
                    Map<String, HashSet<Integer>> relations = new HashMap<>();
                    HashSet<Integer> afters = new HashSet<>();
                    relations.put("after", afters);
                    pages.put(num, relations);
                }
            }

            int before = rule.get(0);
            int after = rule.get(1);
            pages.get(before).get("after").add(after);
        }

        return pages;
    }

    public static ArrayList<ArrayList<ArrayList<Integer>>> readFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner sc = new Scanner(file);
        boolean nextPart = false;

        ArrayList<ArrayList<ArrayList<Integer>>> data = new ArrayList<>();
        ArrayList<ArrayList<Integer>> orderingRules = new ArrayList<>();
        ArrayList<ArrayList<Integer>> pagesToProduce = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (Objects.equals(line, "")) {
                nextPart = true;
            } else if (nextPart) {
                ArrayList<Integer> update = new ArrayList<>();
                String[] updateString = line.split(",");
                for (String s : updateString) {
                    update.add(Integer.parseInt(s));
                }
                pagesToProduce.add(update);
            } else {
                ArrayList<Integer> rule = new ArrayList<>();
                rule.add(Integer.parseInt(line.split("\\|")[0]));
                rule.add(Integer.parseInt(line.split("\\|")[1]));
                orderingRules.add(rule);
            }
        }

        data.add(orderingRules);
        data.add(pagesToProduce);
        return data;
    }
}
