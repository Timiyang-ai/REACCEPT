public DateTimeFormatter withLocale(Locale locale) {
        if (this.locale.equals(locale)) {
            return this;
        }
        return new DateTimeFormatter(printerParser, locale, decimalStyle, resolverStyle, resolverFields, chrono, zone);
    }