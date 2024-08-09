static List<Byte> ofAll(byte[] array) {
        Objects.requireNonNull(array, "array is null");
        return List.ofAll(() -> new Iterator.Impl<Byte>() {
            int i = 0;

            @Override
            public boolean hsNext() {
                return i < array.length;
            }

            @Override
            public Byte getNext() {
                return array[i++];
            }
        });
    }