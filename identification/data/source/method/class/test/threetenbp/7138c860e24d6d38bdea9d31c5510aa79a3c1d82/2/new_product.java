public DateTimeFormatterBuilder appendValueReduced(TemporalField field,
            int width, int maxWidth, int baseValue) {
        Objects.requireNonNull(field, "field");
        ReducedPrinterParser pp = new ReducedPrinterParser(field, width, maxWidth, baseValue);
        if (width == maxWidth) {
            appendFixedWidth(width, pp);
        } else {
            appendInternal(pp);
        }
        return this;
    }