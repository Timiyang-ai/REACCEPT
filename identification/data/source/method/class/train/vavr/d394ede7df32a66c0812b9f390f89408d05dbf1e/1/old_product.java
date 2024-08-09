static List<Boolean> ofAll(boolean[] array) {
        Objects.requireNonNull(array, "array is null");
        return List.ofAll(() -> new Iterator.Impl<Boolean>() {
            int i = 0;

            @Override
            public boolean hsNext() {
                return i < array.length;
            }

            @Override
            public Boolean getNext() {
                return array[i++];
            }
        });
    }