static Iterator<Float> ofAll(float[] array) {
        Objects.requireNonNull(array, "array is null");
        return new Iterator<Float>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < array.length;
            }

            @Override
            public Float next() {
                return array[i++];
            }
        };
    }