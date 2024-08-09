@GwtIncompatible
    public static <T> PriorityQueue<T> tabulate(int size, Function<? super Integer, ? extends T> function) {
        Objects.requireNonNull(function, "function is null");
        final Comparator<? super T> comparator = naturalComparator();
        return Collections.tabulate(size, function, empty(comparator), values -> ofAll(comparator, List.of(values)));
    }