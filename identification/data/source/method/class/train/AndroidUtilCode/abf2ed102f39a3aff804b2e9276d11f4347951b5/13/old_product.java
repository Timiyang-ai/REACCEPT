public static String getString(Date date, DateFormat format, long timeSpan, @TimeConstants.Unit int unit) {
        return millis2String(date2Millis(date) + timeSpan2Millis(timeSpan, unit), format);
    }