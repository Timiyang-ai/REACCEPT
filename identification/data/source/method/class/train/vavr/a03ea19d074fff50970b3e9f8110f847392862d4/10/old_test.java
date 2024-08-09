    @Override
    protected <T> BitSet<T> ofAll(Iterable<? extends T> elements) {
        return this.<T> bsBuilder().ofAll(elements);
    }