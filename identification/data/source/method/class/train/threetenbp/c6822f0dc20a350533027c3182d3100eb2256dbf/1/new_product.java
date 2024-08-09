public static LocalTime parse(String time) {
        ISOChronology.checkNotNull(time, "Text to parse must not be null");
        return DateTimeFormatters.isoLocalTime().parse(time).mergeStrict().toLocalTime();
    }