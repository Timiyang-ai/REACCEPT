public static String getStringByNow(final long timeSpan, @TimeConstants.Unit final int unit) {
        return getStringByNow(timeSpan, getDefaultFormat(), unit);
    }