public final <T extends Temporal> List<T> getDates(final T seed, final T periodStart,
                                   final T periodEnd) {
        return getDates(seed, periodStart, periodEnd, -1);
    }