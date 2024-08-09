public static Date getDate(String time, DateFormat format, long timeSpan, @TimeConstants.Unit int unit) {
        return millis2Date(string2Millis(time, format) + timeSpan2Millis(timeSpan, unit));
    }