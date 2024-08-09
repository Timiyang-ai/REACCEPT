@Override
    public String toString() {
        StringBuilder buf = new StringBuilder(32);
        buf.append("OffsetInfo[")
            .append(isTransition() ? transition : offset)
            .append(']');
        return buf.toString();
    }