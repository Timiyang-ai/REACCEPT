public static String getString(String time, String pattern, long timeSpan, @TimeConstants.Unit int unit) {
        return millis2String(string2Millis(time, pattern) + timeSpan2Millis(timeSpan, unit), pattern);
    }