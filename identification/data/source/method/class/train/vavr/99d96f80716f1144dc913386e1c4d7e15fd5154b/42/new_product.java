static Iterator<Double> ofAll(double[] array) {
        Objects.requireNonNull(array, "array is null");
        return new AbstractIterator<Double>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < array.length;
            }

            @Override
            public Double getNext() {
                return array[i++];
            }
        };
    }