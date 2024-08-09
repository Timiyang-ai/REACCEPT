public OffsetDateTime minus(Duration duration) {
        LocalDateTime newDT = dateTime.minus(duration);
        return (newDT == dateTime ? this : new OffsetDateTime(newDT, offset));
    }