public OffsetDateTime plus(Duration duration) {
        LocalDateTime newDT = dateTime.plus(duration);
        return (newDT == dateTime ? this : new OffsetDateTime(newDT, offset));
    }