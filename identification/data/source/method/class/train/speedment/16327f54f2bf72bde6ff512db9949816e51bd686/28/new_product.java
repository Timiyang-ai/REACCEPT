public MapStream<K, V> filter(BiPredicate<? super K, ? super V> predicate) {
        requireNonNull(predicate);
        return filter(e -> predicate.test(e.getKey(), e.getValue()));
    }