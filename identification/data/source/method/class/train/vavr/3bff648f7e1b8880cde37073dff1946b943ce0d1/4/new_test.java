    @Override
    protected <T> PriorityQueue<T> tabulate(int n, Function<? super Integer, ? extends T> f) {
        return PriorityQueue.tabulate(n, f);
    }