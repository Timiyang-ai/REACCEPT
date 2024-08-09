public static long getTimeSpan(String time0, String time1, DateFormat format, @TimeConstants.Unit int unit) {
        return millis2TimeSpan(Math.abs(string2Millis(time0, format) - string2Millis(time1, format)), unit);
    }