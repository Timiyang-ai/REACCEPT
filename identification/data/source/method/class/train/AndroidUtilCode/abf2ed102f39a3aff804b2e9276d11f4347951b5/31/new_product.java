public static String getString(String time, DateFormat format, long timeSpan, @TimeConstants.Unit int unit) {
        return millis2String(string2Millis(time, format) + timeSpan2Millis(timeSpan, unit), format);
    }