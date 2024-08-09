public final <T extends Temporal> List<T> getDates(final T seed, final Period<T> period) {
        return getDates(seed, period.getStart(), period.getEnd(), -1);
    }