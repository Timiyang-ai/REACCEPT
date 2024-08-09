public static Instant parse(final CharSequence text) {
        DateTimes.checkNotNull(text, "Text to parse must not be null");
        int length = text.length();
        if (length < 2) {
            throw new CalendricalParseException("Instant could not be parsed: " + text, text, 0);
        }
        if (text.charAt(length - 1) != 'Z' && text.charAt(length - 1) != 'z') {
            throw new CalendricalParseException("Instant could not be parsed: " + text, text, length - 1);
        }
        String str = text.toString().replace(',', '.');  //TODO properly, the decimal point may be either a dot or a comma
        return OffsetDateTime.of(LocalDateTime.parse(str.substring(0, length - 1)), ZoneOffset.UTC).toInstant();
    }