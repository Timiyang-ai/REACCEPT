@Override
    public ZonedDateTime withZoneSameInstant(ZoneId zone) {
        Objects.requireNonNull(zone, "zone");
        return this.zone.equals(zone) ? this :
            create(dateTime.toEpochSecond(offset), dateTime.getNano(), zone);
    }