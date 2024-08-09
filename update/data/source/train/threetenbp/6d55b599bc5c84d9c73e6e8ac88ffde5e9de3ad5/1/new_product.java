public ZonedDateTime withLockedOffset() {
        return this.zoneId.equals(offset) ? this : new ZonedDateTime(dateTime, offset, offset);
    }