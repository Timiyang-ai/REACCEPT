ChronoZonedDateTime<C> minusNanos(long nanos) {
        ChronoDateTime newDT = dateTime.toDateTime().minusNanos(nanos);
        return (newDT == dateTime.toDateTime() ? this :
            resolve(newDT, zone, dateTime, ZoneResolvers.retainOffset()));
    }