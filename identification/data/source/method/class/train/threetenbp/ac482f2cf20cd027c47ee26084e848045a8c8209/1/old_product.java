public LocalTime with(TimeAdjuster adjuster) {
        LocalTime time = adjuster.adjustTime(this);
        if (time == null) {
            throw new NullPointerException("TimeAdjuster implementation must not return null");
        }
        return time;
    }