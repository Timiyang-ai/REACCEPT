public static String getUSWeek(final String time, @NonNull final DateFormat format) {
        return getUSWeek(string2Date(time, format));
    }