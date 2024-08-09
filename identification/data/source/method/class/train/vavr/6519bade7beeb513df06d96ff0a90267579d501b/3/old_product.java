static <T> Stream<T> fill(int n, T element) {
        return Stream.ofAll(io.vavr.collection.Collections.fillObject(n, element));
    }