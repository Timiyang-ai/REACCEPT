static <T> List<T> tabulate(int n, Function<? super Integer, ? extends T> f) {
        Objects.requireNonNull(f, "f is null");
        int nOrZero = java.lang.Math.max(n, 0);
        @SuppressWarnings("unchecked")
        T[] elements = (T[]) new Object[nOrZero];
        for (int i = 0; i < n; i++) {
            elements[i] = f.apply(i);
        }
        return List.of(elements);
    }