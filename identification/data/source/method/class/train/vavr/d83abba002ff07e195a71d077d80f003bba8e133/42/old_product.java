@SuppressWarnings("unchecked")
    public static <T> Queue<T> ofAll(java.lang.Iterable<? extends T> elements) {
        Objects.requireNonNull(elements, "elements is null");
        if (elements instanceof Queue) {
            return (Queue<T>) elements;
        } else if (elements instanceof List) {
            return new Queue<>((List<T>) elements, List.empty());
        } else {
            return new Queue<>(List.ofAll(elements), List.empty());
        }
    }