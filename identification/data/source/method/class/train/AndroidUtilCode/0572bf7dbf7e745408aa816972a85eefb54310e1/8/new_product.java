public static String getStringByNow(long timeSpan, DateFormat format, @TimeConstants.Unit int unit) {
        return getString(getNowMills(), format, timeSpan, unit);
    }