public LocalTime with(DateTimeField field, long newValue) {
        if (field instanceof ChronoField) {
            ChronoField f = (ChronoField) field;
            f.checkValidValue(newValue);
            switch (f) {
                case NANO_OF_SECOND: return withNano((int) newValue);
                case NANO_OF_DAY: return LocalTime.ofNanoOfDay(newValue);
                case MICRO_OF_SECOND: return withNano((int) newValue * 1000);
                case MICRO_OF_DAY: return plusNanos((newValue - toNanoOfDay() / 1000) * 1000);
                case MILLI_OF_SECOND: return withNano((int) newValue * 1000_000);
                case MILLI_OF_DAY: return plusNanos((newValue - toNanoOfDay() / 1000_000) * 1000_000);
                case SECOND_OF_MINUTE: return withSecond((int) newValue);
                case SECOND_OF_DAY: return plusSeconds(newValue - toSecondOfDay());
                case MINUTE_OF_HOUR: return withMinute((int) newValue);
                case MINUTE_OF_DAY: return plusMinutes(newValue - (hour * 60 + minute));
                case HOUR_OF_AMPM: return plusHours(newValue - (hour % 12));
                case CLOCK_HOUR_OF_AMPM: return plusHours((newValue == 12 ? 0 : newValue) - (hour % 12));
                case HOUR_OF_DAY: return withHour((int) newValue);
                case CLOCK_HOUR_OF_DAY: return withHour((int) (newValue == 24 ? 0 : newValue));
                case AMPM_OF_DAY: return plusHours((newValue - (hour / 12)) * 12);
            }
            throw new DateTimeException("Unsupported field: " + field.getName());
        }
        return field.doSet(this, newValue);
    }