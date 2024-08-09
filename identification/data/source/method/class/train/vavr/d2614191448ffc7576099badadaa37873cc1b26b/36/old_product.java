static Stream<Integer> ofAll(int[] array) {
        Objects.requireNonNull(array, "array is null");
        return Stream.ofAll(() -> new Iterator.Impl<Integer>() {
            int i = 0;

            @Override
            public boolean hsNext() {
                return i < array.length;
            }

            @Override
            public Integer getNext() {
                return array[i++];
            }
        });
    }