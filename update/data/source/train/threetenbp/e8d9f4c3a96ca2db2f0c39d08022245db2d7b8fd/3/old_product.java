ChronoZonedDateTime<C> withMinute(int minute) {
        ChronoOffsetDateTime<C> newDT = dateTime.withMinute(minute);
        return (newDT == dateTime ? this :
            resolve(newDT.getDateTime(), zone, dateTime, ZoneResolvers.retainOffset()));
    }