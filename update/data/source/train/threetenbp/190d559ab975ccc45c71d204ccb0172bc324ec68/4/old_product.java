@Override
    public ChronoOffsetDateTimeImpl<C> with(DateTimeField field, long newValue) {
        if (field instanceof LocalDateTimeField) {
            LocalDateTimeField f = (LocalDateTimeField) field;
            switch (f) {
                case INSTANT_SECONDS:
                    long epochDays = Jdk8Methods.floorDiv(newValue, SECONDS_PER_DAY);
                    ChronoOffsetDateTimeImpl<C> odt = with(LocalDateTimeField.EPOCH_DAY, epochDays);
                    int secsOfDay = Jdk8Methods.floorMod(newValue, SECONDS_PER_DAY);
                    odt  = odt.with(LocalDateTimeField.SECOND_OF_DAY, secsOfDay);
                    return odt;

                case OFFSET_SECONDS: {
                    return with(dateTime, ZoneOffset.ofTotalSeconds(f.checkValidIntValue(newValue)));
                }
            }
            return with(dateTime.with(field, newValue), offset);
        }
        return field.doSet(this, newValue);
    }