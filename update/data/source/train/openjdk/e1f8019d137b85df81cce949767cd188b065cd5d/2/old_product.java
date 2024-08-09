@Override
    public Temporal adjustInto(Temporal temporal) {
        return temporal
                .with(OFFSET_SECONDS, getOffset().getTotalSeconds())
                .with(EPOCH_DAY, toLocalDate().toEpochDay())
                .with(NANO_OF_DAY, toLocalTime().toNanoOfDay());
    }