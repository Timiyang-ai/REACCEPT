public static String getStringByNow(long timeSpan, @TimeConstants.Unit int unit, String pattern) {
        return millis2String(getNowMills() + timeSpan2Millis(timeSpan, unit), pattern);
    }