public static <T> Array<T> fill(Integer n, Supplier<? extends T> s) {
        return fill(n, anything -> s.get());
    }