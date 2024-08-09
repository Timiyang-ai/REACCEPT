    @Override
    protected <T> Stream<T> tabulate(int n, Function<? super Integer, ? extends T> f) {
        return Stream.tabulate(n, f);
    }