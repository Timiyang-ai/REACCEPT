@SuppressWarnings("unchecked")
    public static <T> Queue<T> ofAll(Iterable<? extends T> elements) {
        Objects.requireNonNull(elements, "elements is null");
        if (elements instanceof Queue) {
            return (Queue<T>) elements;
        } else if (!elements.iterator().hasNext()) {
            return empty();
        } else if (elements instanceof io.vavr.collection.List) {
            return new Queue<>((io.vavr.collection.List<T>) elements, io.vavr.collection.List.empty());
        } else {
            return new Queue<>(io.vavr.collection.List.ofAll(elements), io.vavr.collection.List.empty());
        }
    }