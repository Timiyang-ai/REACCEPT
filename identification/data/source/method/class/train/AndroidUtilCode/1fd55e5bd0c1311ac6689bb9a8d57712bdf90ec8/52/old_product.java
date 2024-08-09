public static int getWeekOfYear(String time, String pattern) {
        Date date = string2Date(time, pattern);
        return getWeekOfYear(date);
    }