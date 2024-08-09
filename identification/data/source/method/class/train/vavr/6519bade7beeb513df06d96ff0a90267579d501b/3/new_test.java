    @Override
    protected <T> Stream<T> fill(int n, Supplier<? extends T> s) {
        return Stream.fill(n, s);
    }