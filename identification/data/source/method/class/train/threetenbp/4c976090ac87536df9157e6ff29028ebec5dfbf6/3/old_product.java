public ZonedDateTime plusNanos(long nanos) {
        return resolveLocal(dateTime.plusNanos(nanos));
    }