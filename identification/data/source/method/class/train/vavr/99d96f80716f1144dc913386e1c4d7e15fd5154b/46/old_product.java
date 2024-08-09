static Iterator<Character> ofAll(char[] array) {
        Objects.requireNonNull(array, "array is null");
        return new AbstractIterator<Character>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < array.length;
            }

            @Override
            public Character getNext() {
                return array[i++];
            }
        };
    }