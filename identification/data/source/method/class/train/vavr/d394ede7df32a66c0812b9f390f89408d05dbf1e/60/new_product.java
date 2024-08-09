static List<Short> ofAll(short[] array) {
        Objects.requireNonNull(array, "array is null");
        return List.ofAll(() -> new Iterator<Short>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < array.length;
            }

            @Override
            public Short next() {
                return array[i++];
            }
        });
    }