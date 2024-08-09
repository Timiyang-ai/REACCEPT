public DateTimeFormatterBuilder appendValueReduced(TemporalField field,
            int width, int baseValue) {
        return appendValueReduced(field, width, width, baseValue);
    }