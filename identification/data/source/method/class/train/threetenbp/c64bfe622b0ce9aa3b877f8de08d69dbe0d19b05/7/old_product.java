public ChronoZonedDateTime<C> withZoneSameLocal(ZoneId zone, ZoneResolver resolver) {
        Objects.requireNonNull(zone, "ZoneId must not be null");
        Objects.requireNonNull(resolver, "ZoneResolver must not be null");
        return zone == this.zone ? this :
            resolve(dateTime.toDateTime(), zone, dateTime, resolver);
    }