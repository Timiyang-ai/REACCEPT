static Stream<Character> ofAll(char[] array) {
        Objects.requireNonNull(array, "array is null");
        return Stream.ofAll(() -> new Iterator.Impl<Character>() {
            int i = 0;

            @Override
            public boolean hsNext() {
                return i < array.length;
            }

            @Override
            public Character getNext() {
                return array[i++];
            }
        });
    }