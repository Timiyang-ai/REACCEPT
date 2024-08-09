public Range restrictTo(Range bounds) {
        boolean startWithin = bounds.contains(getStart());
        boolean endWithin = bounds.contains(getEnd());
        boolean boundsWithin = getStart() < bounds.getStart()
                && getEnd() >= bounds.getEnd();

        if (startWithin) {
            if (endWithin) {
                return this;
            } else {
                return Range.between(getStart(), bounds.getEnd());
            }
        } else {
            if (endWithin) {
                return Range.between(bounds.getStart(), getEnd());
            } else if (boundsWithin) {
                return bounds;
            } else {
                return Range.withLength(getStart(), 0);
            }
        }
    }