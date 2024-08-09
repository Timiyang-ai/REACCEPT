public CalendricalNormalizer parse(CharSequence text) {
        DateTimeFormatter.checkNotNull(text, "Text must not be null");
        String str = text.toString();
        ParsePosition pos = new ParsePosition(0);
        DateTimeParseContext result = parse(str, pos);
        if (pos.getErrorIndex() >= 0 || pos.getIndex() < str.length()) {
            String abbr = str;
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
        return result.toCalendricalMerger();
    }