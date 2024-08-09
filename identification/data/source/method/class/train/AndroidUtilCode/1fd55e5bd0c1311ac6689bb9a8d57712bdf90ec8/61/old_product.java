public static int getWeekOfMonth(String time, String pattern) {
        Date date = string2Date(time, pattern);
        return getWeekOfMonth(date);
    }