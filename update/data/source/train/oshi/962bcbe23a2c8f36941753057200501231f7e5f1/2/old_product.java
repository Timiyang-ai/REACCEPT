public static long parseDHMSOrDefault(String s, long defaultLong) {
        Matcher m = DHMS.matcher(s);
        long seconds = 0L;
        if (m.matches()) {
            if (m.group(1) != null) {
                seconds += parseLongOrDefault(m.group(1), 0L) * 86400L;
            }
            if (m.group(2) != null) {
                seconds += parseLongOrDefault(m.group(2), 0L) * 3600L;
            }
            seconds += parseLongOrDefault(m.group(3), 0L) * 60L;
            seconds += parseLongOrDefault(m.group(4), 0L);
        }
        return seconds;
    }