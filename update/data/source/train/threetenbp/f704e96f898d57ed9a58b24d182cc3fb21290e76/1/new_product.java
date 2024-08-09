public static Period parse(final String text) {
        PeriodFields.checkNotNull(text, "Text to parse must not be null");
        return PeriodParser.getInstance().parse(text);
    }