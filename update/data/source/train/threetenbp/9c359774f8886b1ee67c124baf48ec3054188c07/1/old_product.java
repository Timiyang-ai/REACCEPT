public OffsetDate with(DateTimeField field, long newValue) {
        if (field instanceof LocalDateTimeField) {
            return with(date.with(field, newValue), offset);
        }
        return field.doSet(this, newValue);
    }