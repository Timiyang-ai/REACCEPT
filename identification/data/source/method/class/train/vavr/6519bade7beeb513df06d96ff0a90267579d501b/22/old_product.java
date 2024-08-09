static <T> Stream<T> fill(Integer n, Supplier<? extends T> s) {
        return fill(n, anything -> s.get());
    }