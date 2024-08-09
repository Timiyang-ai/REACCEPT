@Deprecated
    public static int getWeekOfYear(final String time) {
        return getWeekOfYear(string2Date(time, DEFAULT_FORMAT));
    }