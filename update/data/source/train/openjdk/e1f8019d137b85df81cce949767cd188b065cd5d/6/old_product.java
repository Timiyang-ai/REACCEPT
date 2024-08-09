@Override
    public long get(TemporalUnit unit) {
        if (unit == SECONDS) {
            return seconds;
        } else if (unit == NANOS) {
            return nanos;
        } else {
            throw new DateTimeException("Unsupported unit: " + unit.getName());
        }
    }