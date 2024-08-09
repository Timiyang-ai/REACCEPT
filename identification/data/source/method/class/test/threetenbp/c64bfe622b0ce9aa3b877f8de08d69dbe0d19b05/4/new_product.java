ChronoZonedDateTime<C> plusHours(long hours) {
        ChronoDateTime newDT = dateTime.getDateTime().plusHours(hours);
        return (newDT == dateTime.getDateTime() ? this :
            resolve(newDT, zone, dateTime, ZoneResolvers.retainOffset()));
    }