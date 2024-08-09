@Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other instanceof ZoneOffsetTransition) {
            ZoneOffsetTransition d = (ZoneOffsetTransition) other;
            return transition.equals(d.transition) &&
                transitionAfter.getOffset().equals(d.transitionAfter.getOffset());
        }
        return false;
    }