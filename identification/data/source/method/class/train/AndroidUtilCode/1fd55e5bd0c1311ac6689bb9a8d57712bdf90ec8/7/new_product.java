@Deprecated
    public static int getWeekIndex(final String time, @NonNull final DateFormat format) {
        return getWeekIndex(string2Date(time, format));
    }