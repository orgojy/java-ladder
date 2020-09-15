package nextstep.util;

public class StringUtils {

    private StringUtils() {

    }

    public static boolean isNullOrEmpty(String text) {
        if (text == null || text.isEmpty()) {
            return true;
        }
        return text.trim().isEmpty();
    }

    public static String padLeft(String s, int n) {
        return String.format("%" + n + "s", s);
    }

}