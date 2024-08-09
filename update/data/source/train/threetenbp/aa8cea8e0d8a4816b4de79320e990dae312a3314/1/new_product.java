@Override
    public int compareTo(ChronoZonedDateTime<?> other) {
        int cmp = Long.compare(toEpochSecond(), other.toEpochSecond());
        if (cmp == 0) {
            cmp = getTime().getNano() - other.getTime().getNano();
            if (cmp == 0) {
                cmp = getDateTime().compareTo(other.getDateTime());
                if (cmp == 0) {
                    cmp = getZone().getId().compareTo(other.getZone().getId());
                    if (cmp == 0) {
                        cmp = getDate().getChrono().compareTo(other.getDate().getChrono());
                    }
                }
            }
        }
        return cmp;
    }