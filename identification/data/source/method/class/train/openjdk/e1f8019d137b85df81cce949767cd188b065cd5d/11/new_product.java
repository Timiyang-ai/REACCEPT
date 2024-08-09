@Override
    public Instant with(TemporalField field, long newValue) {
        if (field instanceof ChronoField) {
            ChronoField f = (ChronoField) field;
            f.checkValidValue(newValue);
            switch (f) {
                case MILLI_OF_SECOND: {
                    int nval = (int) newValue * 1000_000;
                    return (nval != nanos ? create(seconds, nval) : this);
                }
                case MICRO_OF_SECOND: {
                    int nval = (int) newValue * 1000;
                    return (nval != nanos ? create(seconds, nval) : this);
                }
                case NANO_OF_SECOND: return (newValue != nanos ? create(seconds, (int) newValue) : this);
                case INSTANT_SECONDS: return (newValue != seconds ? create(newValue, nanos) : this);
            }
            throw new UnsupportedTemporalTypeException("Unsupported field: " + field.getName());
        }
        return field.adjustInto(this, newValue);
    }