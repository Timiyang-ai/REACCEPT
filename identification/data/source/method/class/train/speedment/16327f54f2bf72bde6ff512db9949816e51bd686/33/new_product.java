public MapStream<K, V> filterValue(Predicate<? super V> predicate) {
        requireNonNull(predicate);
        return filter(e -> predicate.test(e.getValue()));
    }