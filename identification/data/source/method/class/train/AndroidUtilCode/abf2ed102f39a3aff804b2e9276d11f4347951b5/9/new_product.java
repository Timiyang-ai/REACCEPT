public static String getZodiac(final String time, @NonNull final DateFormat format) {
        return getZodiac(string2Date(time, format));
    }