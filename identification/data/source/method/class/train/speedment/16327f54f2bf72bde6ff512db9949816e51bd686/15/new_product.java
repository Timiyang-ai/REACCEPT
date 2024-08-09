public MapStream<K, V> filterKey(Predicate<? super K> predicate) {
        requireNonNull(predicate);
        return filter(e -> predicate.test(e.getKey()));
    }