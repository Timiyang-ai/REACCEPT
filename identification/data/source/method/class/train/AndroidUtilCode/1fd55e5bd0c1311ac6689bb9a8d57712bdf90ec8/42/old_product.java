public static int getWeekOfMonth(final String time, final DateFormat format) {
        return getWeekOfMonth(string2Date(time, format));
    }