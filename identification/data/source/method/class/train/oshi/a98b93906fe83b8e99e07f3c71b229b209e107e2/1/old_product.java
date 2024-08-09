public static Date cimDateTimeToDate(String cimDate) {
        // Keep first 18 digits; ignore next 3
        // Keep + or - sign of timezone
        // Parse last 3 digits from minutes to HH:mm
        int tzMinutes = Integer.parseInt(cimDate.substring(22));
        return cimDateFormat.parse(String.format("%s%c%02d%02d", cimDate.substring(0, 18), cimDate.charAt(21),
                tzMinutes / 60, tzMinutes % 60), new ParsePosition(0));
    }