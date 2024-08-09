public ZonedDateTime minus(Period period) {
        return (ZonedDateTime) period.subtractFrom(this);
    }