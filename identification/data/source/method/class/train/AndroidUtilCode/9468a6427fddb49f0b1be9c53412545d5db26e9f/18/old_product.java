public static String getFitTimeSpanByNow(long millis, int precision) {
        return getFitTimeSpan(System.currentTimeMillis(), millis, precision);
    }