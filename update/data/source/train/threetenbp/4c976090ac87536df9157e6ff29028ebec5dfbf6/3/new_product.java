public ZonedDateTime plusSeconds(long seconds) {
        return resolveInstant(dateTime.plusSeconds(seconds));
    }