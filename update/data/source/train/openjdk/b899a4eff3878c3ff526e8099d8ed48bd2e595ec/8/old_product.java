public String getText(TextStyle style, Locale locale) {
        return new DateTimeFormatterBuilder().appendText(DAY_OF_WEEK, style).toFormatter(locale).print(this);
    }