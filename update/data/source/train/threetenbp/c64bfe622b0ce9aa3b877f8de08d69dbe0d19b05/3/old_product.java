ChronoZonedDateTime<C> plusSeconds(long seconds) {
        ChronoDateTime newDT = dateTime.toDateTime().plusSeconds(seconds);
        return (newDT == dateTime.toDateTime() ? this :
            resolve(newDT, zone, dateTime, ZoneResolvers.retainOffset()));
    }