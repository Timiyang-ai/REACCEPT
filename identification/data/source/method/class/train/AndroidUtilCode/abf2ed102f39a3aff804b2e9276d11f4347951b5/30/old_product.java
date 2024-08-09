public static String getZodiac(String time) {
        return getZodiac(string2Date(time, DEFAULT_PATTERN));
    }