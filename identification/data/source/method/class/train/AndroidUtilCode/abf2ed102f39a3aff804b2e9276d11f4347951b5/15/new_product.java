public static String getChineseZodiac(final String time) {
        return getChineseZodiac(string2Date(time, getDefaultFormat()));
    }