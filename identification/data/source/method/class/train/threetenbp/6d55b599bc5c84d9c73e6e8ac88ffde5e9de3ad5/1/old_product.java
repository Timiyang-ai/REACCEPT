public ZonedDateTime withZoneLocked() {
        return this.zoneId.equals(offset) ? this : new ZonedDateTime(dateTime, offset, offset);
    }