@Override
    public Temporal adjustInto(Temporal temporal) {
        return temporal
                .with(EPOCH_DAY, toLocalDate().toEpochDay())
                .with(NANO_OF_DAY, toLocalTime().toNanoOfDay())
                .with(OFFSET_SECONDS, getOffset().getTotalSeconds());
    }