@Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("Transition[")
            .append(isGap() ? "Gap" : "Overlap")
            .append(" at ")
            .append(transition)
            .append(" to ")
            .append(transitionAfter.getOffset())
            .append(']');
        return buf.toString();
    }