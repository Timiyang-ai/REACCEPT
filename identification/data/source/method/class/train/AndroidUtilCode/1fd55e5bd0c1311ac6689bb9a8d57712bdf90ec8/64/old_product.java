public static long getTimeSpanByNow(String time, TimeUnit unit, SimpleDateFormat format) {
        return getTimeSpan(getCurTimeString(), time, unit, format);
    }