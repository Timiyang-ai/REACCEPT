public static int getWeekIndex(String time, String pattern) {
        Date date = string2Date(time, pattern);
        return getWeekIndex(date);
    }