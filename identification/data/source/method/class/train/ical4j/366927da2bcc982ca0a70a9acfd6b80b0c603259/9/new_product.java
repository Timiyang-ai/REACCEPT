public final <T extends Temporal> List<T> getDates(final T periodStart, final T periodEnd) {
        return getDates(periodStart, periodStart, periodEnd, -1);
    }