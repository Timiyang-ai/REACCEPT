@Override
    public ZonedDateTime with(DateTimeField field, long newValue) {
        if (field instanceof ChronoField) {
            ChronoField f = (ChronoField) field;
            switch (f) {
                case INSTANT_SECONDS: return create(newValue, getNano(), zoneId);
                case OFFSET_SECONDS: {
                    ZoneOffset offset = ZoneOffset.ofTotalSeconds(f.checkValidIntValue(newValue));
                    return resolveOffset(offset);
                }
            }
            return resolveLocal(dateTime.with(field, newValue));
        }
        return field.doWith(this, newValue);
    }