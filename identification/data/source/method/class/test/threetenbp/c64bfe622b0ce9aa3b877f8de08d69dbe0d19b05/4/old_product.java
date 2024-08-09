ChronoZonedDateTime<C> plusHours(long hours) {
        ChronoDateTime newDT = dateTime.toDateTime().plusHours(hours);
        return (newDT == dateTime.toDateTime() ? this :
            resolve(newDT, zone, dateTime, ZoneResolvers.retainOffset()));
    }