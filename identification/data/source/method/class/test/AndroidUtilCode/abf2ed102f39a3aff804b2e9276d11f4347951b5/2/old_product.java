public static Date getDate(String time, String pattern, long timeSpan, @TimeConstants.Unit int unit) {
        return millis2Date(string2Millis(time, pattern) + timeSpan2Millis(timeSpan, unit));
    }