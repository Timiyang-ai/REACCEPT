@Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("OffsetInfo[")
            .append(dateTime)
            .append(' ')
            .append(isTransition() ? transition : offset)
            .append(']');
        return buf.toString();
    }