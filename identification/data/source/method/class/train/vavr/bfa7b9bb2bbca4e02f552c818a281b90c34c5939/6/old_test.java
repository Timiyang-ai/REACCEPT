    @Override
    protected <T> Array<T> tabulate(int n, Function<? super Integer, ? extends T> f) {
        return Array.tabulate(n, f);
    }