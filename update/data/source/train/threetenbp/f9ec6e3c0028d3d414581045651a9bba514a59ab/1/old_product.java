public ZonedDateTime atStartOfDayInZone(ZoneId zone) {
        return ZonedDateTime.of(this, LocalTime.MIDNIGHT, zone, ZoneResolvers.postGapPreOverlap());
    }