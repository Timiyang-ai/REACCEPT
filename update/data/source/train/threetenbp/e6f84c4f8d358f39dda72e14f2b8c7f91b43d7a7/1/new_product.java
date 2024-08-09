public OffsetDateTime atTime(LocalTime time) {
        return OffsetDateTime.of(date, time, offset);
    }