ChronoZonedDateTime<C> minusSeconds(long seconds) {
        ChronoDateTime newDT = dateTime.toDateTime().minusSeconds(seconds);
        return (newDT == dateTime.toDateTime() ? this :
            resolve(newDT, zone, dateTime, ZoneResolvers.retainOffset()));
    }