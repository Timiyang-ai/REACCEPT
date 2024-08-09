@Override
    public ZonedDateTime withEarlierOffsetAtOverlap() {
        ZoneOffsetTransition trans = getZone().getRules().getTransition(getDateTime());
        if (trans != null) {
            ZoneOffset offset = trans.getOffsetBefore();
            if (offset.equals(getOffset()) == false) {
                OffsetDateTime newDT = dateTime.withOffsetSameLocal(offset);
                return new ZonedDateTime(newDT, zone);
            }
        }
        return this;
    }