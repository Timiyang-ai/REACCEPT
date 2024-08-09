static Iterator<Byte> ofAll(byte... elements) {
        Objects.requireNonNull(elements, "elements is null");
        return new AbstractIterator<Byte>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < elements.length;
            }

            @Override
            public Byte getNext() {
                return elements[i++];
            }
        };
    }