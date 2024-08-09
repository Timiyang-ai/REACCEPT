    @Override
    protected <T> BitSet<T> tabulate(int n, Function<? super Integer, ? extends T> f) {
        return this.<T> bsBuilder().tabulate(n, f);
    }