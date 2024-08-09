public static <K extends Comparable<? super K>, V> TreeMap<K, V> fill(int n, Supplier<? extends Tuple2<? extends K, ? extends V>> s) {
        Objects.requireNonNull(s, "s is null");
        return fill((Comparator<? super K> & Serializable) K::compareTo, n, s);
    }