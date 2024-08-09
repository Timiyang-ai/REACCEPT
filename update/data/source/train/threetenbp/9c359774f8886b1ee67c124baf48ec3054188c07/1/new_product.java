public OffsetDate with(DateTimeField field, long newValue) {
        if (field instanceof LocalDateTimeField) {
            LocalDateTimeField f = (LocalDateTimeField) field;
            switch (f) {
                case OFFSET_SECONDS: {
                    return with(date, ZoneOffset.ofTotalSeconds(f.checkValidIntValue(newValue)));
                }
            }
            return with(date.with(field, newValue), offset);
        }
        return field.doSet(this, newValue);
    }