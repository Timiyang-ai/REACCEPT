static Iterator<Boolean> ofAll(boolean[] array) {
        Objects.requireNonNull(array, "array is null");
        return new AbstractIterator<Boolean>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < array.length;
            }

            @Override
            public Boolean getNext() {
                return array[i++];
            }
        };
    }