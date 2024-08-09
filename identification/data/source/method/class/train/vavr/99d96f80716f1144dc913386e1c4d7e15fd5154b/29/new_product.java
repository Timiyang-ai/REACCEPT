@SafeVarargs
    static <T> Iterator<T> of(T... elements) {
        Objects.requireNonNull(elements, "elements is null");
        return new AbstractIterator<T>() {

            int index = 0;

            @Override
            public boolean hasNext() {
                return index < elements.length;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    EMPTY.next();
                }
                return elements[index++];
            }
        };
    }