static <T> Iterator<T> fill(int n, Supplier<? extends T> s) {
        return tabulate(n, anything -> s.get());
    }