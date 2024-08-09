public static <T extends Comparable<? super T>> TreeSet<T> fill(int n, Supplier<? extends T> s) {
        Objects.requireNonNull(s, "s is null");
        return fill((Comparator<? super T> & Serializable) T::compareTo, n, s);
    }