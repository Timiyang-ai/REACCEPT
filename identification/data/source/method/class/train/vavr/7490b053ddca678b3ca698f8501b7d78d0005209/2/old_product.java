static <T> PriorityQueue<T> fill(int size, Supplier<? extends T> supplier) {
        Objects.requireNonNull(supplier, "supplier is null");
        final Comparator<? super T> comparator = naturalComparator();
        return Collections.fill(size, supplier, empty(comparator), values -> ofAll(comparator, List.of(values)));
    }