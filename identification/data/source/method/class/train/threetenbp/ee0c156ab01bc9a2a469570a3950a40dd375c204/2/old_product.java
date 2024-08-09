public static Instant parse(final CharSequence text) {
        return DateTimeFormatters.isoInstant().parse(text, Instant.class);
    }