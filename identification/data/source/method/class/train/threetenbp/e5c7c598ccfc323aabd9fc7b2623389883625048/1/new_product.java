@Override
    public String toString() {
        // TODO: optimize
        return OffsetDateTime.dateTime(this, ZoneOffset.UTC).toLocalDateTime().toString() + 'Z';
    }