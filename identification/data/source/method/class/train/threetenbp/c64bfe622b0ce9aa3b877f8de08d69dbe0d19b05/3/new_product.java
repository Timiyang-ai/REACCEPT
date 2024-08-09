ChronoZonedDateTime<C> minusNanos(long nanos) {
        ChronoDateTime newDT = dateTime.getDateTime().minusNanos(nanos);
        return (newDT == dateTime.getDateTime() ? this :
            resolve(newDT, zone, dateTime, ZoneResolvers.retainOffset()));
    }