public static <T> Queue<T> fill(int n, Supplier<? extends T> s) {
        return tabulate(n, anything -> s.get());
    }