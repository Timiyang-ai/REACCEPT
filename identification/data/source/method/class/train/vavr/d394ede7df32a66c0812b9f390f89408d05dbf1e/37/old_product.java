static List<Long> ofAll(long[] array) {
        Objects.requireNonNull(array, "array is null");
        return List.ofAll(() -> new Iterator<Long>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < array.length;
            }

            @Override
            public Long next() {
                return array[i++];
            }
        });
    }