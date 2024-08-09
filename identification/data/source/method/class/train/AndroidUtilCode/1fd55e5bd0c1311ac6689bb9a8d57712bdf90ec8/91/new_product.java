public static int getWeekOfYear(String time) {
        return getWeekOfYear(string2Date(time, DEFAULT_PATTERN));
    }