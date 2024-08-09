@Deprecated
    public static int getWeekOfMonth(final String time, @NonNull final DateFormat format) {
        return getWeekOfMonth(string2Date(time, format));
    }