public static <T> Vector<T> fill(int n, Supplier<? extends T> s) {
        return tabulate(n, anything -> s.get());
    }