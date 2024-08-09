public static long getMillis(String time, DateFormat format, long timeSpan, @TimeConstants.Unit int unit) {
        return string2Millis(time, format) + timeSpan2Millis(timeSpan, unit);
    }