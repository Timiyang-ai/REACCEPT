public boolean intersects(final Range other) {
        if (!isEmpty() && !other.isEmpty()) {
            return getStart() < other.getEnd() && other.getStart() < getEnd();
        } else {
            return false;
        }
    }