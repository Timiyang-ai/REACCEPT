public ZonedDateTime plusSeconds(long seconds) {
        return resolveLocal(dateTime.plusSeconds(seconds));
    }