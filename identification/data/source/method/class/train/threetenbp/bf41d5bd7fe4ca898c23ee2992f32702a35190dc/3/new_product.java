public LocalTime with(DateTimeField field, long newValue) {
        if (field instanceof LocalDateTimeField) {
            LocalDateTimeField f = (LocalDateTimeField) field;
            f.checkValidValue(newValue);
            switch (f) {
                case NANO_OF_SECOND: return withNano((int) newValue);
                case NANO_OF_DAY: return LocalTime.ofNanoOfDay(newValue);
                case MICRO_OF_SECOND: return withNano((int) newValue * 1000);
                case MICRO_OF_DAY: return plusNanos((newValue - toNanoOfDay() / 1000) * 1000);
                case MILLI_OF_SECOND: return withNano((int) newValue * 1000000);
                case MILLI_OF_DAY: return plusNanos((newValue - toNanoOfDay() / 1000000) * 1000000);
                case SECOND_OF_MINUTE: return withSecond((int) newValue);
                case SECOND_OF_DAY: return plusSeconds(newValue - toSecondOfDay());
                case MINUTE_OF_HOUR: return withMinute((int) newValue);
                case MINUTE_OF_DAY: return plusMinutes(newValue - (getHour() * 60 + getMinute()));
                case HOUR_OF_AMPM: return plusHours(newValue - (getHour() % 12));
                case HOUR_OF_DAY: return withHour((int) newValue);
                case AMPM_OF_DAY: return plusHours((newValue - (getHour() / 12)) * 12);
            }
            throw new CalendricalException(field.getName() + " not valid for LocalTime");
        }
        return field.set(this, newValue);
    }