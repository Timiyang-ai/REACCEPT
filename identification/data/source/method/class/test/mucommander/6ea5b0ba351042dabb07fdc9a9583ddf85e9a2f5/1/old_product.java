public static boolean endsWithIgnoreCase(String a, String b) {
        int bLength;

        return a.regionMatches(true, a.length() - (bLength = b.length()), b, 0, bLength);
    }