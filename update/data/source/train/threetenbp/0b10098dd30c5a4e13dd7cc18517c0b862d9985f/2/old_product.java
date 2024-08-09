@Override
    public boolean equals(Object otherInfo) {
        if (this == otherInfo) {
           return true;
        }
        if (otherInfo instanceof ZoneOffsetInfo) {
            ZoneOffsetInfo info = (ZoneOffsetInfo) otherInfo;
            return dateTime.equals(info.dateTime) &&
                    (transition != null ? transition.equals(info.transition) : offset.equals(info.offset));
        }
        return false;
    }