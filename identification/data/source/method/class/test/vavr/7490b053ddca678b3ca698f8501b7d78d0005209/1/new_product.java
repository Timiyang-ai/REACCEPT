@GwtIncompatible
    @SuppressWarnings("unchecked")
    public static <T> PriorityQueue<T> fill(int size, Supplier<? extends T> supplier) {
        Objects.requireNonNull(supplier, "supplier is null");
        final Comparator<? super T> comparator = Comparators.naturalComparator();
        return io.vavr.collection.Collections.fill(size, supplier, empty(comparator), values -> ofAll(comparator, io.vavr.collection.List.of(values)));
    }