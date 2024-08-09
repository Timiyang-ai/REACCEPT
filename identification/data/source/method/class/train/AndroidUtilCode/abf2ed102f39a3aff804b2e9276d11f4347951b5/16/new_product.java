public static String getZodiac(final String time, final DateFormat format) {
        return getZodiac(string2Date(time, format));
    }