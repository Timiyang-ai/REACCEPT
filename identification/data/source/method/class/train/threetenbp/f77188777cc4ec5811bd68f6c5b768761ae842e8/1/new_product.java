public OffsetDateTime plus(Duration duration) {
        LocalDateTime newDT = dateTime.plusSeconds(duration.getSeconds()).plusNanos(duration.getNano());
        return (newDT == dateTime ? this : new OffsetDateTime(newDT, offset));
    }