import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    public static void main(String[] args) throws Exception {
        System.out.println(mullItOverPart1("input/day3"));
        System.out.println(mullItOverPart2("input/day3"));
    }

    public static int mullItOverPart1(String fileName) throws IOException {
        String input = readFile(fileName);
        int result = 0;

        Pattern pattern = Pattern.compile("(?<=mul\\()(\\d+,\\d+)(?=\\))", Pattern.MULTILINE);

        Matcher matcher = pattern.matcher(input);
        result += getMulsSum(matcher);

        return result;
    }

    public static int mullItOverPart2(String fileName) throws IOException {
        String input = readFile(fileName);

        Pattern substringPattern = Pattern.compile("(?<=do(?!n't)\\(\\)|^).+?(?=don't\\(\\)|$)", Pattern.MULTILINE);
        ArrayList<String> substrings = new ArrayList<>();
        Matcher subStringmatcher = substringPattern.matcher(input);

        while (subStringmatcher.find()) {
            substrings.add(subStringmatcher.group());
        }

        int result = 0;
        Pattern pattern = Pattern.compile("(?<=mul\\()(\\d+,\\d+)(?=\\))", Pattern.MULTILINE);

        for (String substring : substrings) {
            Matcher matcher = pattern.matcher(substring);
            result += getMulsSum(matcher);
        }

        return result;
    }

    public static String readFile(String fileName) throws IOException {
        File file = new File(fileName);
        Scanner sc = new Scanner(file);

        StringBuilder fileWithLinesStripped = new StringBuilder();

        while (sc.hasNextLine()) {
            fileWithLinesStripped.append(sc.nextLine());
        }

        return fileWithLinesStripped.toString();
    }

    public static int getMulsSum(Matcher matcher) {
        int sum = 0;

        while (matcher.find()) {
            String[] stringArr = matcher.group().split(",");
            sum += Math.multiplyExact(Integer.parseInt(stringArr[0]), Integer.parseInt(stringArr[1]));
        }

        return sum;
    }
}
