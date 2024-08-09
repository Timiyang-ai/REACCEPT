@SafeVarargs
    static <T> Stream<T> of(T... elements) {
        Objects.requireNonNull(elements, "elements is null");
        return Stream.ofAll(new Iterator<T>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < elements.length;
            }

            @Override
            public T next() {
                return elements[i++];
            }
        });
    }