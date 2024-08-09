static Iterator<Integer> ofAll(int... elements) {
        Objects.requireNonNull(elements, "elements is null");
        return new AbstractIterator<Integer>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < elements.length;
            }

            @Override
            public Integer getNext() {
                return elements[i++];
            }
        };
    }