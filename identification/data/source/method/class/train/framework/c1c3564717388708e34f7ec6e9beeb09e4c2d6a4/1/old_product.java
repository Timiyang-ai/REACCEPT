public Range restrictTo(Range bounds) {
        boolean startWithin = getStart() >= bounds.getStart();
        boolean endWithin = getEnd() <= bounds.getEnd();

        if (startWithin) {
            if (endWithin) {
                return this;
            } else {
                return Range.between(getStart(), bounds.getEnd());
            }
        } else {
            if (endWithin) {
                return Range.between(bounds.getStart(), getEnd());
            } else {
                return bounds;
            }
        }
    }