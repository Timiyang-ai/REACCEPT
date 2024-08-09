    @Override
    protected <T> BitSet<T> fill(int n, Supplier<? extends T> s) {
        return this.<T> bsBuilder().fill(n, s);
    }