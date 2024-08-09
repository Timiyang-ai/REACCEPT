public DateTimeBuilder parseToBuilder(CharSequence text) {
        DateTimes.checkNotNull(text, "Text must not be null");
        String str = text.toString();  // parsing whole String, so this makes sense
        ParsePosition pos = new ParsePosition(0);
        DateTimeParseContext result = parseToContext(str, pos);
        if (pos.getErrorIndex() >= 0 || pos.getIndex() < str.length()) {
            String abbr = str.toString();
            if (abbr.length() > 64) {
                abbr = abbr.substring(0, 64) + "...";
            }
            if (pos.getErrorIndex() >= 0) {
                throw new CalendricalParseException("Text '" + abbr + "' could not be parsed at index " +
                        pos.getErrorIndex(), str, pos.getErrorIndex());
            } else {
                throw new CalendricalParseException("Text '" + abbr + "' could not be parsed, unparsed text found at index " +
                        pos.getIndex(), str, pos.getIndex());
            }
        }
        return result.toBuilder();
    }