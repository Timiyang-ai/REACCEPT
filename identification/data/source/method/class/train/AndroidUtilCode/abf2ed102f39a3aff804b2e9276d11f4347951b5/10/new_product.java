public static String getChineseZodiac(final String time, @NonNull final DateFormat format) {
        return getChineseZodiac(string2Date(time, format));
    }