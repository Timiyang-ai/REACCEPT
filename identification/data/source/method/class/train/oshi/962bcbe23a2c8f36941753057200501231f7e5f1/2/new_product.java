public static long parseDHMSOrDefault(String s, long defaultLong) {
        Matcher m = DHMS.matcher(s);
        long milliseconds = 0L;
        if (m.matches()) {
            if (m.group(1) != null) {
                milliseconds += parseLongOrDefault(m.group(1), 0L) * 86400000L;
            }
            if (m.group(2) != null) {
                milliseconds += parseLongOrDefault(m.group(2), 0L) * 3600000L;
            }
            milliseconds += parseLongOrDefault(m.group(3), 0L) * 60000L;
            milliseconds += parseLongOrDefault(m.group(4), 0L) * 1000L;
            milliseconds += 1000 * parseDoubleOrDefault("0." + m.group(5), 0d);
        }
        return milliseconds;
    }