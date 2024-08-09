public static String getNowString(final DateFormat format) {
        return millis2String(System.currentTimeMillis(), format);
    }