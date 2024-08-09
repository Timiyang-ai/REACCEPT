public static String repeat(String str, int count) {
        if (str == null)
            return null;
        if (count < 1)
            return EMPTY_STRING;
        StringBuilder builder = new StringBuilder(str.length() * count);
        for (int i = 0; i < count; i++) {
            builder.append(str);
        }
        return builder.toString();
    }