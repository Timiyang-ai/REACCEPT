static Iterator<Byte> ofAll(byte[] array) {
        Objects.requireNonNull(array, "array is null");
        return new Iterator<Byte>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < array.length;
            }

            @Override
            public Byte next() {
                return array[i++];
            }
        };
    }