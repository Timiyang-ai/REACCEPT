static Iterator<Long> ofAll(long... elements) {
        Objects.requireNonNull(elements, "elements is null");
        return new AbstractIterator<Long>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < elements.length;
            }

            @Override
            public Long getNext() {
                return elements[i++];
            }
        };
    }