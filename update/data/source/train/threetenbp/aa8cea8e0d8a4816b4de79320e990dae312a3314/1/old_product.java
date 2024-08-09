@Override
    public int compareTo(ChronoZonedDateTime<?> other) {
        int cmp = getOffsetDateTime().compareTo(other.getOffsetDateTime());
        if (cmp == 0) {
            cmp = getZone().getId().compareTo(other.getZone().getId());
        }
        return cmp;
    }