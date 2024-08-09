static Iterator<Double> ofAll(double... elements) {
        Objects.requireNonNull(elements, "elements is null");
        return new AbstractIterator<Double>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < elements.length;
            }

            @Override
            public Double getNext() {
                return elements[i++];
            }
        };
    }