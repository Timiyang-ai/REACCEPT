public static <T extends Comparable<? super T>> TreeSet<T> tabulate(int n, Function<? super Integer, ? extends T> f) {
        Objects.requireNonNull(f, "f is null");
        return tabulate(naturalComparator(), n, f);
    }