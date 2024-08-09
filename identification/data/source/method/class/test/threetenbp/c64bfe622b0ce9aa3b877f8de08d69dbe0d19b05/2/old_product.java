ChronoZonedDateTime<C> minusHours(long hours) {
        ChronoDateTime newDT = dateTime.toDateTime().minusHours(hours);
        return (newDT == dateTime.toDateTime() ? this :
            resolve(newDT, zone, dateTime, ZoneResolvers.retainOffset()));
    }