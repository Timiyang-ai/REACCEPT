static <T> Stream<T> tabulate(int n, Function<? super Integer, ? extends T> f) {
        Objects.requireNonNull(f, "f is null");
        return Stream.ofAll(io.vavr.collection.Collections.tabulate(n, f));
    }