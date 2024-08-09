@Deprecated
    public static int getWeekOfYear(final long millis) {
        return getWeekOfYear(millis2Date(millis));
    }