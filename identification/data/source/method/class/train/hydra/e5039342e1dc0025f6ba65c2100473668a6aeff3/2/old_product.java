public final boolean filter(final Bundle row) {
        initOnceOnly();
        return filterExec(row);
    }