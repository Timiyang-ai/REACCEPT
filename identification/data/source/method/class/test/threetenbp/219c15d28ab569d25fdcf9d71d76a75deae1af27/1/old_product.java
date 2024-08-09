@Override
    public ZonedDateTime atZone(ZoneId zone) {
        return ZonedDateTime.of(this, zone, ZoneResolvers.postGapPreOverlap());
    }