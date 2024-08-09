public static int getWeekIndex(String time) {
        return getWeekIndex(string2Date(time, DEFAULT_PATTERN));
    }