static Iterator<Short> ofAll(short... elements) {
        Objects.requireNonNull(elements, "elements is null");
        return new AbstractIterator<Short>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < elements.length;
            }

            @Override
            public Short getNext() {
                return elements[i++];
            }
        };
    }