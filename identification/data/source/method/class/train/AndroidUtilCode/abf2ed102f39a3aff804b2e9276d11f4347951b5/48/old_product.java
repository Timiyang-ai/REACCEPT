public static Date getDate(Date date, long timeSpan, @TimeConstants.Unit int unit) {
        return millis2Date(date2Millis(date) + timeSpan2Millis(timeSpan, unit));
    }