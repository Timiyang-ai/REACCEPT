    @Override
    protected <T> Queue<T> tabulate(int n, Function<? super Integer, ? extends T> f) {
        return Queue.tabulate(n, f);
    }