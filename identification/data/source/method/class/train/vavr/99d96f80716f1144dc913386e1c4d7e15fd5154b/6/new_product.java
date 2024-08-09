static Iterator<Float> ofAll(float... elements) {
        Objects.requireNonNull(elements, "elements is null");
        return new Iterator<Float>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < elements.length;
            }

            @Override
            public Float next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return elements[i++];
            }
        };
    }