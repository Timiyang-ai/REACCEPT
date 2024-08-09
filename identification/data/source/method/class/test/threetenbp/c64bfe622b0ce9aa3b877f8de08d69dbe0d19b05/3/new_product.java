ChronoZonedDateTime<C> minusMinutes(long minutes) {
        ChronoDateTime newDT = dateTime.getDateTime().minusMinutes(minutes);
        return (newDT == dateTime.getDateTime() ? this :
            resolve(newDT, zone, dateTime, ZoneResolvers.retainOffset()));
    }