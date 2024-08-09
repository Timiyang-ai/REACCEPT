@Override
    public long get(DateTimeField field) {
        if (field instanceof LocalDateTimeField) {
            switch ((LocalDateTimeField) field) {
                case NANO_OF_SECOND: return nano;
                case NANO_OF_DAY: return toNanoOfDay();
                case MICRO_OF_SECOND: return nano / 1000;
                case MICRO_OF_DAY: return toNanoOfDay() / 1000;
                case MILLI_OF_SECOND: return nano / 1000000;
                case MILLI_OF_DAY: return toNanoOfDay() / 1000000;
                case SECOND_OF_MINUTE: return second;
                case SECOND_OF_DAY: return toSecondOfDay();
                case MINUTE_OF_HOUR: return minute;
                case MINUTE_OF_DAY: return hour * 60 + minute;
                case HOUR_OF_AMPM: return hour % 12;
                case CLOCK_HOUR_OF_AMPM: int ham = hour % 12; return (ham % 12 == 0 ? 12 : ham);
                case HOUR_OF_DAY: return hour;
                case CLOCK_HOUR_OF_DAY: return (hour == 0 ? 24 : hour);
                case AMPM_OF_DAY: return hour / 12;
            }
            throw new CalendricalException(field.getName() + " not valid for LocalTime");
        }
        return field.get(this);
    }