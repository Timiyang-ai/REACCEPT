static Iterator<Float> ofAll(float... elements) {
        Objects.requireNonNull(elements, "elements is null");
        return new AbstractIterator<Float>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < elements.length;
            }

            @Override
            public Float getNext() {
                return elements[i++];
            }
        };
    }