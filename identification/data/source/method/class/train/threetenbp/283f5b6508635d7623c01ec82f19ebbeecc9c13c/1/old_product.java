public ZonedDateTime minus(Period period) {
        LocalDateTime oldDT = dateTime.toLocalDateTime();
        LocalDateTime newDT = oldDT.minus(period);
        return (newDT == oldDT ? this :
            resolve(newDT, zone, dateTime, ZoneResolvers.retainOffset()));
    }