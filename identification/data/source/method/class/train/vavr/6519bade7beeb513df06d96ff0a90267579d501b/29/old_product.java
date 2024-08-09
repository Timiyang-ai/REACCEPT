public static <T> Queue<T> fill(Integer n, Function<Integer, ? extends T> f) {
        Objects.requireNonNull(n, "n is null");
        Objects.requireNonNull(f, "f is null");
        return new Queue<>(List.fill(n, f), List.empty());
    }