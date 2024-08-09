public static String getChineseZodiac(String time) {
        return getChineseZodiac(string2Date(time, DEFAULT_PATTERN));
    }