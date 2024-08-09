static <T> Stream<T> fill(Integer n, Supplier<? extends T> s) {
        return tabulate(n, anything -> s.get());
    }