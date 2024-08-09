@Override
    public ZonedDateTime withLaterOffsetAtOverlap() {
        ZoneOffsetTransition trans = getZone().getRules().getTransition(getDateTime());
        if (trans != null) {
            ZoneOffset laterOffset = trans.getOffsetAfter();
            if (laterOffset.equals(offset) == false) {
                return new ZonedDateTime(dateTime, laterOffset, zone);
            }
        }
        return this;
    }