static <T> Iterator<T> tabulate(int n, Function<? super Integer, ? extends T> f) {
        Objects.requireNonNull(f, "f is null");
        return new AbstractIterator<T>() {

            int i = 0;

            @Override
            public boolean hasNext() {
                return i < n;
            }

            @Override
            protected T getNext() {
                return f.apply(i++);
            }
        };
    }