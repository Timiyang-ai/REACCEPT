public static <T> Vector<T> fill(Integer n, Supplier<? extends T> s) {
        return fill(n, anything -> s.get());
    }