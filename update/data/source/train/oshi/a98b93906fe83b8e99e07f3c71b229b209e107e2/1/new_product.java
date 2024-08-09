public static long cimDateTimeToMillis(String cimDate) {
        // Keep first 22 characters: digits, decimal, and + or - sign of
        // time zone. Parse last 3 digits from minutes to HH:mm
        try {
            int tzInMinutes = Integer.parseInt(cimDate.substring(22));
            LocalTime offsetAsLocalTime = LocalTime.MIN.plusMinutes(tzInMinutes);
            OffsetDateTime dateTime = OffsetDateTime.parse(
                    cimDate.substring(0, 22) + offsetAsLocalTime.format(DateTimeFormatter.ISO_LOCAL_TIME), CIM_FORMAT);
            return dateTime.toInstant().toEpochMilli();
        } catch (IndexOutOfBoundsException // if cimDate not 22+ chars
                | NumberFormatException // if TZ minutes doesn't parse
                | DateTimeParseException e) {
            return 0L;
        }
    }