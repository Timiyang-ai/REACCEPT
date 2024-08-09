public DateTimeParseContext parseToContext(CharSequence text, ParsePosition position) {
        DateTimes.checkNotNull(text, "Text must not be null");
        DateTimes.checkNotNull(position, "ParsePosition must not be null");
        DateTimeParseContext context = new DateTimeParseContext(locale, symbols);
        int pos = position.getIndex();
        pos = printerParser.parse(context, text, pos);
        if (pos < 0) {
            position.setErrorIndex(~pos);
            return null;
        }
        position.setIndex(pos);
        return context;
    }