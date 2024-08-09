public DateTimeFormatterBuilder appendValueReduced(
            TemporalField field, int width, int baseValue) {
        Objects.requireNonNull(field, "field");
        ReducedPrinterParser pp = new ReducedPrinterParser(field, width, baseValue);
        appendFixedWidth(width, pp);
        return this;
    }