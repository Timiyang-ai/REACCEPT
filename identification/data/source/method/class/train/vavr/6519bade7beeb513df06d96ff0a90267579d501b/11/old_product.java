public static <T> Queue<T> tabulate(Integer n, Function<Integer, ? extends T> f) {
        Objects.requireNonNull(n, "n is null");
        Objects.requireNonNull(f, "f is null");
        return new Queue<>(List.tabulate(n, f), List.empty());
    }