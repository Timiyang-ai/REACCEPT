public static Date getDateByNow(long timeSpan, @TimeConstants.Unit int unit) {
        return getDate(getNowMills(), timeSpan, unit);
    }