default boolean inPeriod(ZonedDateTime timestamp) {
        return timestamp != null
                && !timestamp.isBefore(getBeginTime())
                && timestamp.isBefore(getEndTime());
    }