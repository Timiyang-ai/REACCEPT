public ZonedDateTime withDateTime(LocalDateTime dateTime, ZoneResolver resolver) {
        Objects.requireNonNull(dateTime, "LocalDateTime");
        Objects.requireNonNull(resolver, "ZoneResolver");
        return this.getDateTime().equals(dateTime) ?
                this : ZonedDateTime.resolve(dateTime, zone, this.dateTime, resolver);
    }