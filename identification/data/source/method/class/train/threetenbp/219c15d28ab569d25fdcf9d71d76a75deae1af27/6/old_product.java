@Override
    public ZonedDateTime withZoneSameInstant(ZoneId zone) {
        return zone == this.zone ? this : ofInstant(dateTime, zone);
    }