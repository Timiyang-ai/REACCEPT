public static Period parse(CharSequence text) {
        Objects.requireNonNull(text, "text");
        Matcher matcher = PATTERN.matcher(text);
        if (matcher.matches()) {
            int negate = ("-".equals(matcher.group(1)) ? -1 : 1);
            String yearMatch = matcher.group(2);
            String monthMatch = matcher.group(3);
            String dayMatch = matcher.group(4);
            if (yearMatch != null || monthMatch != null || dayMatch != null) {
                try {
                    return create(parseNumber(text, yearMatch, negate),
                            parseNumber(text, monthMatch, negate),
                            parseNumber(text, dayMatch, negate));
                } catch (NumberFormatException ex) {
                    throw (DateTimeParseException) new DateTimeParseException("Text cannot be parsed to a Period", text, 0).initCause(ex);
                }
            }
        }
        throw new DateTimeParseException("Text cannot be parsed to a Period", text, 0);
    }