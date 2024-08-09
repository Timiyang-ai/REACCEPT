@Override
    public LocalTime with(CalendricalAdjuster adjuster) {
        if (adjuster instanceof TimeAdjuster) {
            return ((TimeAdjuster) adjuster).adjustTime(this);
        } else if (adjuster instanceof LocalTime) {
            return ((LocalTime) adjuster);
        }
        DateTimes.checkNotNull(adjuster, "Adjuster must not be null");
        throw new CalendricalException("Unable to adjust LocalTime with " + adjuster.getClass().getSimpleName());
    }