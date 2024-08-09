static Iterator<Boolean> ofAll(boolean... elements) {
        Objects.requireNonNull(elements, "elements is null");
        return new Iterator<Boolean>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < elements.length;
            }

            @Override
            public Boolean next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return elements[i++];
            }
        };
    }