public static int getWeekOfYear(final String time, final DateFormat format) {
        return getWeekOfYear(string2Date(time, format));
    }