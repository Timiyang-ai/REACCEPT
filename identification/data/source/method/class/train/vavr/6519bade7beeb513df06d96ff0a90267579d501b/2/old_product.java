public static <T> Queue<T> fill(Integer n, Supplier<? extends T> s) {
        return fill(n, anything -> s.get());
    }