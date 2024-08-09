public static <T> Array<T> tabulate(int n, Function<? super Integer, ? extends T> f) {
        Objects.requireNonNull(f, "f is null");
        int nOrZero = java.lang.Math.max(n, 0);
        Object[] elements = new Object[nOrZero];
        for (int i = 0; i < n; i++) {
            elements[i] = f.apply(i);
        }
        return wrap(elements);
    }