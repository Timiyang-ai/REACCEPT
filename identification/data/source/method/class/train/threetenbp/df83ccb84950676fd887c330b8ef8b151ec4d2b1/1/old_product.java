public DateTimeFormatter withLocale(Locale locale) {
        DateTimes.checkNotNull(locale, "Locale must not be null");
        if (locale.equals(this.locale)) {
            return this;
        }
        return new DateTimeFormatter(locale, symbols, printerParser);
    }