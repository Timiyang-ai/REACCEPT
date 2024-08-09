static List<Boolean> ofAll(boolean[] array) {
        Objects.requireNonNull(array, "array is null");
        return List.ofAll(() -> new Iterator<Boolean>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < array.length;
            }

            @Override
            public Boolean next() {
                return array[i++];
            }
        });
    }