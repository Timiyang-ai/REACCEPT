public static long getMillisByNow(long timeSpan, @TimeConstants.Unit int unit) {
        return getNowMills() + timeSpan2Millis(timeSpan, unit);
    }