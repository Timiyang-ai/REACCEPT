static Iterator<Character> ofAll(char... elements) {
        Objects.requireNonNull(elements, "elements is null");
        return new AbstractIterator<Character>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < elements.length;
            }

            @Override
            public Character getNext() {
                return elements[i++];
            }
        };
    }