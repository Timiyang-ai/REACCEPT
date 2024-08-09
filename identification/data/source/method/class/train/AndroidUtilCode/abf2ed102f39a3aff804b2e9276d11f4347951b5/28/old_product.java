public static String getNowString(DateFormat format) {
        return millis2String(System.currentTimeMillis(), format);
    }