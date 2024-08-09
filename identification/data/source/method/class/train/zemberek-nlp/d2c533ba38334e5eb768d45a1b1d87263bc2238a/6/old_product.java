public static String insertFromRight(String str, int interval, String stringToInsert) {
        if (interval < 0)
            throw new IllegalArgumentException("interval value cannot be negative.");
        if (str == null || interval == 0 || interval >= str.length() || isNullOrEmpty(stringToInsert))
            return str;
        StringBuilder b = new StringBuilder();
        int j = 0;
        for (int i = str.length() - 1; i >= 0; i--) {
            b.append(str.charAt(i));
            j++;
            if (j % interval == 0 && j <= str.length() - 1)
                b.append(stringToInsert);
        }
        return reverse(b.toString());
    }