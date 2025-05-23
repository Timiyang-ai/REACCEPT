static Iterator<Float> ofAll(float[] array) {
        Objects.requireNonNull(array, "array is null");
        return new AbstractIterator<Float>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < array.length;
            }

            @Override
            public Float getNext() {
                return array[i++];
            }
        };
    }