public static long getTimeSpanByNow(Date date, @TimeConstant.Unit int unit) {
        return getTimeSpan(new Date(), date, unit);
    }