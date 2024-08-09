public ZonedDateTime plusHours(long hours) {
        return resolveLocal(dateTime.plusHours(hours));
    }