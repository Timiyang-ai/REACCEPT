ChronoZonedDateTime<C> plusNanos(long nanos) {
        ChronoDateTime newDT = dateTime.getDateTime().plusNanos(nanos);
        return (newDT == dateTime.getDateTime() ? this :
            resolve(newDT, zone, dateTime, ZoneResolvers.retainOffset()));
    }