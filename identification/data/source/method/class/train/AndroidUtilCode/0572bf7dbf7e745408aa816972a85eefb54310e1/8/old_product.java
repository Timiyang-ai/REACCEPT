public static String getStringByNow(long timeSpan, @TimeConstants.Unit int unit, String pattern) {
        return getString(getNowMills(), pattern, timeSpan, unit);
    }