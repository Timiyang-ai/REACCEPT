static Iterator<Integer> ofAll(int[] array) {
        Objects.requireNonNull(array, "array is null");
        return new AbstractIterator<Integer>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < array.length;
            }

            @Override
            public Integer getNext() {
                return array[i++];
            }
        };
    }