static Stream<Long> ofAll(long[] array) {
        Objects.requireNonNull(array, "array is null");
        return Stream.ofAll(() -> new Iterator.Impl<Long>() {
            int i = 0;

            @Override
            public boolean hsNext() {
                return i < array.length;
            }

            @Override
            public Long getNext() {
                return array[i++];
            }
        });
    }