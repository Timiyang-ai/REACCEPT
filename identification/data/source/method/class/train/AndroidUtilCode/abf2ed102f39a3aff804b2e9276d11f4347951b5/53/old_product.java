public static Date getDate(long millis, long timeSpan, @TimeConstants.Unit int unit) {
        return millis2Date(millis + timeSpan2Millis(timeSpan, unit));
    }