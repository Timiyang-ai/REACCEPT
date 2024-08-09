public DateTimeFormatterBuilder appendFraction(
            DateTimeField field, int minWidth, int maxWidth) {
        appendInternal(new FractionPrinterParser(field, minWidth, maxWidth));
        return this;
    }