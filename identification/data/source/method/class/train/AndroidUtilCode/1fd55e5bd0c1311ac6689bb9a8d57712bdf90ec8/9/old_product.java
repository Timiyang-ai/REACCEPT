public static long getTimeSpanByNow(String time, DateFormat format, @TimeConstants.Unit int unit) {
        return getTimeSpan(getNowString(format), time, format, unit);
    }