static Vector<Double> ofAll(double[] array) {
        Objects.requireNonNull(array, "array is null");
        return Vector.ofAll(() -> new Iterator<Double>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < array.length;
            }

            @Override
            public Double next() {
                return array[i++];
            }
        });
    }