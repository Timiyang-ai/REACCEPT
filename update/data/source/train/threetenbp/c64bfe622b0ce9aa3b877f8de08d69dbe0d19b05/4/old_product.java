ChronoZonedDateTime<C> plusNanos(long nanos) {
        ChronoDateTime newDT = dateTime.toDateTime().plusNanos(nanos);
        return (newDT == dateTime.toDateTime() ? this :
            resolve(newDT, zone, dateTime, ZoneResolvers.retainOffset()));
    }