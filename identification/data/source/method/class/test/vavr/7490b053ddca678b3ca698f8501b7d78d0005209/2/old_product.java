@SuppressWarnings("unchecked")
    public static <T> PriorityQueue<T> fill(int size, T element) {
        final Comparator<? super T> comparator = Comparators.naturalComparator();
        return io.vavr.collection.Collections.fillObject(size, element, empty(comparator), values -> ofAll(comparator, io.vavr.collection.List.of(values)));
    }