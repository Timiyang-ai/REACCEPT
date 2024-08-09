static <T> List<T> tabulate(int n, Function<? super Integer, ? extends T> f) {
        Objects.requireNonNull(f, "f is null");
        n = n < 0 ? 0 : n;
        @SuppressWarnings("unchecked")
        T[] elements = (T[]) new Object[n];
        for (int i = 0; i < n; i++) {
            elements[i] = f.apply(i);
        }
        return List.of(elements);
    }