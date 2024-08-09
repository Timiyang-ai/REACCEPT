public ZonedDateTime plus(Period period) {
        LocalDateTime oldDT = dateTime.toLocalDateTime();
        LocalDateTime newDT = oldDT.plus(period);
        return (newDT == oldDT ? this :
            resolve(newDT, zone, dateTime, ZoneResolvers.retainOffset()));
    }