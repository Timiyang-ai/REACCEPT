public static long getMillisByNow(long timeSpan, @TimeConstants.Unit int unit) {
        return getMillis(getNowMills(), timeSpan, unit);
    }