@Override
    public ChronoZonedDateTime<C> withLaterOffsetAtOverlap() {
        ZoneOffsetTransition trans = getZone().getRules().getTransition(LocalDateTime.from(this));
        if (trans != null) {
            ZoneOffset offset = trans.getOffsetAfter();
            if (offset.equals(getOffset()) == false) {
                return new ChronoZonedDateTimeImpl<C>(dateTime, offset, zoneId);
            }
        }
        return this;
    }