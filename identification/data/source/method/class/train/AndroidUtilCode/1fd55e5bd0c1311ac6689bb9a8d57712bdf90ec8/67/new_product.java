public static long getTimeSpan(String time0, String time1, TimeUnit unit, String pattern) {
        return millis2Unit(Math.abs(string2Millis(time0, pattern) - string2Millis(time1, pattern)), unit);
    }