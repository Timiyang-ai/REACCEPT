public static <T> Stream<T> fill(int n, Supplier<? extends T> s) {
        Objects.requireNonNull(s, "s is null");
        return Stream.ofAll(io.vavr.collection.Collections.fill(n, s));
    }