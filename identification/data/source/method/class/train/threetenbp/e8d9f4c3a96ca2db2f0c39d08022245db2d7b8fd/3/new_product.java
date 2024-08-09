@Override
    public ChronoZonedDateTime<C> withZoneSameLocal(ZoneId zone, ZoneResolver resolver) {
        Objects.requireNonNull(zone, "zone");
        Objects.requireNonNull(resolver, "resolver");
        return zone == this.zoneId ? this :
            resolve(dateTime.getDateTime(), zone, dateTime, resolver);
    }