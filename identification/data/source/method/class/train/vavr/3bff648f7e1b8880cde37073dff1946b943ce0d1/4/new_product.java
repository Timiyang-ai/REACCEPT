public static <T> PriorityQueue<T> tabulate(int size, Function<? super Integer, ? extends T> function) {
        Objects.requireNonNull(function, "function is null");
        final Comparator<? super T> comparator = Comparators.naturalComparator();
        return io.vavr.collection.Collections.tabulate(size, function, empty(comparator), values -> ofAll(comparator, io.vavr.collection.List.of(values)));
    }