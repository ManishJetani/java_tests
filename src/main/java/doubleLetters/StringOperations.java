package doubleLetters;

public class StringOperations {
    public static int getDoubleLetterCounts(String input) {
        int counts = 0;
        if (input == null || input.length() < 2) {
            return counts;
        }

        char[] chars = input.toCharArray();

        for (int index = 0; index < chars.length -1; index++) {
            if (chars[index] == chars[index+1]) {
                counts++;
            }
        }
        return counts;
    }
}
