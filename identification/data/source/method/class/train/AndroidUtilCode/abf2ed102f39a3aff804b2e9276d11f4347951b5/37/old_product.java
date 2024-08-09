public static long getMillis(String time, long timeSpan, @TimeConstants.Unit int unit) {
        return getMillis(time, DEFAULT_FORMAT, timeSpan, unit);
    }