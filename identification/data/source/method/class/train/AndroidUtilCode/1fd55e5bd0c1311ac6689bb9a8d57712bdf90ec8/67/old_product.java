public static long getTimeSpan(String time0, String time1, TimeUnit unit, SimpleDateFormat format) {
        return millis2Unit(Math.abs(string2Millis(time0, format) - string2Millis(time1, format)), unit);
    }