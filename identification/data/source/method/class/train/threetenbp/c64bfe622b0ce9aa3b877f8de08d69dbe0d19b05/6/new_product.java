ChronoZonedDateTime<C> plusSeconds(long seconds) {
        ChronoDateTime newDT = dateTime.getDateTime().plusSeconds(seconds);
        return (newDT == dateTime.getDateTime() ? this :
            resolve(newDT, zone, dateTime, ZoneResolvers.retainOffset()));
    }