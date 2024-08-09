public boolean intersects(final Range other) {
        return getStart() < other.getEnd() && other.getStart() < getEnd();
    }