static List<Integer> ofAll(int[] array) {
        Objects.requireNonNull(array, "array is null");
        return List.ofAll(() -> new Iterator<Integer>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < array.length;
            }

            @Override
            public Integer next() {
                return array[i++];
            }
        });
    }