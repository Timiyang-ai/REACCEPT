public static boolean isToday(final long millis) {
        long wee = (System.currentTimeMillis() / TimeConstants.DAY) * TimeConstants.DAY - 8 * TimeConstants.HOUR;
        return millis >= wee && millis < wee + TimeConstants.DAY;
    }