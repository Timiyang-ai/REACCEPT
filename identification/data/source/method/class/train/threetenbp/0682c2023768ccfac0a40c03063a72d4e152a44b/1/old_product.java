public ZonedDateTime with(DateAdjuster adjuster) {
        return with(adjuster, ZoneResolvers.retainOffset());
    }