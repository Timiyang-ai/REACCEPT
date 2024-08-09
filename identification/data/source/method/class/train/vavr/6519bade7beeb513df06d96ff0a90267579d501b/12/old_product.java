public static <T> Queue<T> tabulate(int n, Function<? super Integer, ? extends T> f) {
        Objects.requireNonNull(f, "f is null");
        return new Queue<>(List.tabulate(n, f), List.empty());
    }