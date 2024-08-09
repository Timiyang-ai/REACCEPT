@Override
    public long get(DateTimeField field) {
        if (field instanceof LocalDateTimeField) {
            switch ((LocalDateTimeField) field) {
                case NANO_OF_SECOND: return getNano();
                case NANO_OF_DAY: return toNanoOfDay();
                case MICRO_OF_SECOND: return getNano() / 1000;
                case MICRO_OF_DAY: return toNanoOfDay() / 1000;
                case MILLI_OF_SECOND: return getNano() / 1000000;
                case MILLI_OF_DAY: return toNanoOfDay() / 1000000;
                case SECOND_OF_MINUTE: return getSecond();
                case SECOND_OF_DAY: return toSecondOfDay();
                case MINUTE_OF_HOUR: return getMinute();
                case MINUTE_OF_DAY: return getHour() * 60 + getMinute();
                case HOUR_OF_AMPM: return getHour() % 12;
                case HOUR_OF_DAY: return getHour();
                case AMPM_OF_DAY: return getHour() / 12;
            }
            throw new CalendricalException(field.getName() + " not valid for LocalTime");
        }
        return field.get(this);
    }