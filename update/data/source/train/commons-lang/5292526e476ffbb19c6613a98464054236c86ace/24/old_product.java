public StrBuilder appendAll(final Iterable<?> iterable) {
        if (iterable != null) {
            for (Object o : iterable) {
                append(o);
            }
        }
        return this;
    }