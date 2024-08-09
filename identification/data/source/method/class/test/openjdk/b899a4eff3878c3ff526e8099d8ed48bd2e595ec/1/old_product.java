@Override
    public <R extends Temporal> SimplePeriod between(R dateTime1, R dateTime2) {
        return new SimplePeriod(dateTime1.periodUntil(dateTime2, this), this);
    }