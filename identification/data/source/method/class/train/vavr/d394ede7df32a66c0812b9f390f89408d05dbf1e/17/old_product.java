static List<Double> ofAll(double[] array) {
        Objects.requireNonNull(array, "array is null");
        return List.ofAll(() -> new Iterator.Impl<Double>() {
            int i = 0;

            @Override
            public boolean hsNext() {
                return i < array.length;
            }

            @Override
            public Double getNext() {
                return array[i++];
            }
        });
    }