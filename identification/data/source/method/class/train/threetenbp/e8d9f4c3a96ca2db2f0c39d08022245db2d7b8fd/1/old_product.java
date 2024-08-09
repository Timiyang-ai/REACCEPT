@Override
    public ChronoZonedDateTime<C> withEarlierOffsetAtOverlap() {
        ZoneOffsetTransition trans = getZone().getRules().getTransition(LocalDateTime.from(this));
        if (trans != null) {
            ZoneOffset offset = trans.getOffsetBefore();
            if (offset.equals(getOffset()) == false) {
                ChronoOffsetDateTimeImpl<C> newDT = dateTime.withOffsetSameLocal(offset);
                return new ChronoZonedDateTimeImpl<C>(newDT, zone);
            }
        }
        return this;
    }