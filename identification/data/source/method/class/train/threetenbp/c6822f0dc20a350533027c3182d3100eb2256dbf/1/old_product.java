public static LocalTime parse(String time) {
        ISOChronology.checkNotNull(time, "Text to parse must not be null");
        return PARSER.parse(time).mergeStrict().toLocalTime();
    }