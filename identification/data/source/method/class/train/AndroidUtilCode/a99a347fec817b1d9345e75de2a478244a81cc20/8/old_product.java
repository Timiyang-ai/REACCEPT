public static boolean isToday(final long millis) {
        long wee = getTimeTodayZero();
        return millis >= wee && millis < wee + TimeConstants.DAY;
    }