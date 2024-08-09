static List<Float> ofAll(float[] array) {
        Objects.requireNonNull(array, "array is null");
        return List.ofAll(() -> new Iterator.Impl<Float>() {
            int i = 0;

            @Override
            public boolean hsNext() {
                return i < array.length;
            }

            @Override
            public Float getNext() {
                return array[i++];
            }
        });
    }