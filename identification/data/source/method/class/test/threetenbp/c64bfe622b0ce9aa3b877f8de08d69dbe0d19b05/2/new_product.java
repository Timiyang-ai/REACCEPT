ChronoZonedDateTime<C> minusHours(long hours) {
        ChronoDateTime newDT = dateTime.getDateTime().minusHours(hours);
        return (newDT == dateTime.getDateTime() ? this :
            resolve(newDT, zone, dateTime, ZoneResolvers.retainOffset()));
    }