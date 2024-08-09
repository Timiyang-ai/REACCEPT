public static String getZodiac(final String time) {
        return getZodiac(string2Date(time, getDefaultFormat()));
    }