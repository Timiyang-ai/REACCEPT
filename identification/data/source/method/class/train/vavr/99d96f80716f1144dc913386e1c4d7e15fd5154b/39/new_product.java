static Iterator<Byte> ofAll(byte... elements) {
        Objects.requireNonNull(elements, "elements is null");
        return new Iterator<Byte>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < elements.length;
            }

            @Override
            public Byte next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return elements[i++];
            }
        };
    }