public StrBuilder appendAll(final Iterable<?> iterable) {
        if (iterable != null) {
            for (final Object o : iterable) {
                append(o);
            }
        }
        return this;
    }