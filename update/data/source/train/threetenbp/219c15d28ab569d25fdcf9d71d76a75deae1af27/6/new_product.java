public static LocalDate from(DateTimeAccessor dateTime) {
        if (dateTime instanceof LocalDate) {
            return (LocalDate) dateTime;
        } else if (dateTime instanceof LocalDateTime) {
            return ((LocalDateTime) dateTime).getDate();
        } else if (dateTime instanceof ZonedDateTime) {
            return ((ZonedDateTime) dateTime).getDate();
        }
        // handle builder as a special case
        if (dateTime instanceof DateTimeBuilder) {
            DateTimeBuilder builder = (DateTimeBuilder) dateTime;
            LocalDate date = builder.extract(LocalDate.class);
            if (date != null) {
                return date;
            }
        }
        return ofEpochDay(dateTime.getLong(EPOCH_DAY));
    }