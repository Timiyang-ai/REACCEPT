public static long getTimeSpan(Date date0, Date date1, ConstUtils.TimeUnit unit) {
        return ConvertUtils.millis2TimeSpan(Math.abs(date2Millis(date0) - date2Millis(date1)), unit);
    }