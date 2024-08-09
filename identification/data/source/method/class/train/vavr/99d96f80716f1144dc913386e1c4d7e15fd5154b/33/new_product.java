static Iterator<Character> ofAll(char... elements) {
        Objects.requireNonNull(elements, "elements is null");
        return new Iterator<Character>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < elements.length;
            }

            @Override
            public Character next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return elements[i++];
            }
        };
    }