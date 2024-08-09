public static int getWeekOfMonth(final String time) {
        return getWeekOfMonth(string2Date(time, DEFAULT_FORMAT));
    }