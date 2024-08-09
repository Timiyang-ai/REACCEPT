public static String getString(final String time,
                                   final long timeSpan,
                                   @TimeConstants.Unit final int unit) {
        return getString(time, getDefaultFormat(), timeSpan, unit);
    }