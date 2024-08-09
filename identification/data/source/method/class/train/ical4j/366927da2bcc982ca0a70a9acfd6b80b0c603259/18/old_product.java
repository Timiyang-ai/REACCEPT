public final DateList getDates(final Date seed, final Period period,
            final Value value) {
        return getDates(seed, period.getStart(), period.getEnd(), value);
    }