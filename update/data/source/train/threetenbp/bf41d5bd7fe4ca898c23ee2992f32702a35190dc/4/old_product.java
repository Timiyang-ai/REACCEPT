@Override
    public long get(DateTimeField field) {
        if (field instanceof LocalDateTimeField) {
            switch ((LocalDateTimeField) field) {
                case NANO_OF_SECOND: return getNanoOfSecond();
                case NANO_OF_DAY: return toNanoOfDay();
                case MICRO_OF_SECOND: return getNanoOfSecond() / 1000;
                case MICRO_OF_DAY: return toNanoOfDay() / 1000;
                case MILLI_OF_SECOND: return getNanoOfSecond() / 1000000;
                case MILLI_OF_DAY: return toNanoOfDay() / 1000000;
                case SECOND_OF_MINUTE: return getSecondOfMinute();
                case SECOND_OF_DAY: return toSecondOfDay();
                case MINUTE_OF_HOUR: return getMinuteOfHour();
                case MINUTE_OF_DAY: return getHourOfDay() * 60 + getMinuteOfHour();
                case HOUR_OF_AMPM: return getHourOfDay() % 12;
                case HOUR_OF_DAY: return getHourOfDay();
                case AMPM_OF_DAY: return getHourOfDay() / 12;
            }
            throw new CalendricalException(field.getName() + " not valid for LocalTime");
        }
        return field.get(this);
    }