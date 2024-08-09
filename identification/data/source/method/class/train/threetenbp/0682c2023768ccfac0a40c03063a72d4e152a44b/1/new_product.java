public ZonedDateTime with(DateTimeAdjuster adjuster) {
        return with(adjuster, ZoneResolvers.retainOffset());
    }