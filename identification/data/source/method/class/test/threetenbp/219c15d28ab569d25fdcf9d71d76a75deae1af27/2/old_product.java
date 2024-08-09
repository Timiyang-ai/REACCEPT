@Override
    public ZonedDateTime withLaterOffsetAtOverlap() {
        ZoneOffsetTransition trans = getZone().getRules().getTransition(getDateTime());
        if (trans != null) {
            ZoneOffset offset = trans.getOffsetAfter();
            if (offset.equals(getOffset()) == false) {
                OffsetDateTime newDT = dateTime.withOffsetSameLocal(offset);
                return new ZonedDateTime(newDT, zone);
            }
        }
        return this;
    }