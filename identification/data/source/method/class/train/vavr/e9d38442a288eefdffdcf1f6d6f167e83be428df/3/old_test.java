    @Override
    protected <T> Iterator<T> tabulate(int n, Function<? super Integer, ? extends T> f) {
        return Iterator.tabulate(n, f);
    }