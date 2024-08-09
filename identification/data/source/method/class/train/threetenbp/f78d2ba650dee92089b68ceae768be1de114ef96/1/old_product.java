@Override
    public Instant plus(TemporalAdder adder) {
        return (Instant) adder.addTo(this);
    }