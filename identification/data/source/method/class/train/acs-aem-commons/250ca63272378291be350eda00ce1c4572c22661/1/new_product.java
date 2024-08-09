public static boolean isRichText(String str) {
        Matcher m = RICH_TEXT_PATTERN.matcher(str);

        return m.find();
    }