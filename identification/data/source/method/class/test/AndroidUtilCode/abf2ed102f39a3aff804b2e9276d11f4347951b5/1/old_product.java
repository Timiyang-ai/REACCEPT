public static long getMillis(String time, String pattern, long timeSpan, @TimeConstants.Unit int unit) {
        return string2Millis(time, pattern) + timeSpan2Millis(timeSpan, unit);
    }