public static long getMillis(Date date, long timeSpan, @TimeConstants.Unit int unit) {
        return date2Millis(date) + timeSpan2Millis(timeSpan, unit);
    }