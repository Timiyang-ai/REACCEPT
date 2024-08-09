@Deprecated
    public static int getWeekOfYear(final String time, @NonNull final DateFormat format) {
        return getWeekOfYear(string2Date(time, format));
    }