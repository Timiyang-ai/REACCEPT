static Iterator<Long> ofAll(long... elements) {
        Objects.requireNonNull(elements, "elements is null");
        return new Iterator<Long>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < elements.length;
            }

            @Override
            public Long next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return elements[i++];
            }
        };
    }