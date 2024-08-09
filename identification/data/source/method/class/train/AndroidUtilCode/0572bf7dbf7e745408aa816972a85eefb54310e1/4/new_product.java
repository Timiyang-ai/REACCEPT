public static String getStringByNow(final long timeSpan,
                                        @NonNull final DateFormat format,
                                        @TimeConstants.Unit final int unit) {
        return getString(getNowMills(), format, timeSpan, unit);
    }