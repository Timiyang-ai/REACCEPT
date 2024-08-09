ChronoZonedDateTime<C> minusMinutes(long minutes) {
        ChronoDateTime newDT = dateTime.toDateTime().minusMinutes(minutes);
        return (newDT == dateTime.toDateTime() ? this :
            resolve(newDT, zone, dateTime, ZoneResolvers.retainOffset()));
    }