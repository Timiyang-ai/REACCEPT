static Iterator<Boolean> ofAll(boolean... elements) {
        Objects.requireNonNull(elements, "elements is null");
        return new AbstractIterator<Boolean>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < elements.length;
            }

            @Override
            public Boolean getNext() {
                return elements[i++];
            }
        };
    }