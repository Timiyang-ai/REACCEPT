private DateTimeBuilder parseToBuilder(final CharSequence text, final ParsePosition position) {
        ParsePosition pos = (position != null ? position : new ParsePosition(0));
        Parsed result = parseUnresolved0(text, pos);
        if (result == null || pos.getErrorIndex() >= 0 || (position == null && pos.getIndex() < text.length())) {
            String abbr = "";
            if (text.length() > 64) {
                abbr = text.subSequence(0, 64).toString() + "...";
            } else {
                abbr = text.toString();
            }
            if (pos.getErrorIndex() >= 0) {
                throw new DateTimeParseException("Text '" + abbr + "' could not be parsed at index " +
                        pos.getErrorIndex(), text, pos.getErrorIndex());
            } else {
                throw new DateTimeParseException("Text '" + abbr + "' could not be parsed, unparsed text found at index " +
                        pos.getIndex(), text, pos.getIndex());
            }
        }
        return result.resolveFields().toBuilder();
    }