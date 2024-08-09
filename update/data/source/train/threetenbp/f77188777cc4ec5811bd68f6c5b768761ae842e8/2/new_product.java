public OffsetDateTime minus(Duration duration) {
        LocalDateTime newDT = dateTime.minusSeconds(duration.getSeconds()).minusNanos(duration.getNano());
        return (newDT == dateTime ? this : new OffsetDateTime(newDT, offset));
    }