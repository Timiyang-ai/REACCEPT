public static <T> Vector<T> fill(Integer n, Supplier<? extends T> s) {
        return tabulate(n, anything -> s.get());
    }