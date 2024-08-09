ChronoZonedDateTime<C> minusSeconds(long seconds) {
        ChronoDateTime newDT = dateTime.getDateTime().minusSeconds(seconds);
        return (newDT == dateTime.getDateTime() ? this :
            resolve(newDT, zone, dateTime, ZoneResolvers.retainOffset()));
    }