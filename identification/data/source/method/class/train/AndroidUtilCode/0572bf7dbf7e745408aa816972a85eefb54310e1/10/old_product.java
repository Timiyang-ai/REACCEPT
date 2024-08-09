public static Date getDateByNow(long timeSpan, @TimeConstants.Unit int unit) {
        return millis2Date(getNowMills() + timeSpan2Millis(timeSpan, unit));
    }