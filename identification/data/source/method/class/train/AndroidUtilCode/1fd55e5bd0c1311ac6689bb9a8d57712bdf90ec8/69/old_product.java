public static long getTimeSpanByNow(Date date, @TimeConstants.Unit int unit) {
        return getTimeSpan(new Date(), date, unit);
    }