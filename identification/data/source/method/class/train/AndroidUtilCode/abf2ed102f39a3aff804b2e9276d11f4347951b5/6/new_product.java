public static Date getDate(String time, long timeSpan, @TimeConstants.Unit int unit) {
        return getDate(time, DEFAULT_FORMAT, timeSpan, unit);
    }