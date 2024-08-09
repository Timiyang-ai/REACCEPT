public <R extends Chronology<R>> ChronoZonedDateTime<R> withDateTime(ChronoDateTime<R> newDateTime, ZoneResolver resolver) {
        Objects.requireNonNull(newDateTime, "ChronoDateTime must not be null");
        Objects.requireNonNull(resolver, "ZoneResolver must not be null");
        if (dateTime.toDateTime().equals(newDateTime)) {
            return (ChronoZonedDateTime<R>)this;
        } else {
            return resolve(newDateTime, zone, this.dateTime, resolver);
        }
    }