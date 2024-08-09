public static String getChineseWeek(final String time, @NonNull final DateFormat format) {
        return getChineseWeek(string2Date(time, format));
    }