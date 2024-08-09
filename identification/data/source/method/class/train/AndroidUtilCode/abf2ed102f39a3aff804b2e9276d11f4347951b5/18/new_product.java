public static boolean isLeapYear(final String time, @NonNull final DateFormat format) {
        return isLeapYear(string2Date(time, format));
    }