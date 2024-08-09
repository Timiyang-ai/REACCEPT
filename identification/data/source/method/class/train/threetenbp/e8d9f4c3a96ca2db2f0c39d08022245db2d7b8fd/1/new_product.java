@Override
    public ChronoZonedDateTime<C> withEarlierOffsetAtOverlap() {
        ZoneOffsetTransition trans = getZone().getRules().getTransition(LocalDateTime.from(this));
        if (trans != null && trans.isOverlap()) {
            ZoneOffset earlierOffset = trans.getOffsetBefore();
            if (earlierOffset.equals(offset) == false) {
                return new ChronoZonedDateTimeImpl<C>(dateTime, earlierOffset, zoneId);
            }
        }
        return this;
    }