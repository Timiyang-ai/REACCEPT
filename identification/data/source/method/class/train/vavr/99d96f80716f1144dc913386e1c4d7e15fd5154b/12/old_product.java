static Iterator<Short> ofAll(short[] array) {
        Objects.requireNonNull(array, "array is null");
        return new AbstractIterator<Short>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < array.length;
            }

            @Override
            public Short getNext() {
                return array[i++];
            }
        };
    }