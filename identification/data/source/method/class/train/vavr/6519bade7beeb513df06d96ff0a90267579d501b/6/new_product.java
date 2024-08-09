static <T> Stream<T> fill(int n, Supplier<? extends T> s) {
        Objects.requireNonNull(s, "s is null");
        return Stream.ofAll(Collections.fill(n, s));
    }