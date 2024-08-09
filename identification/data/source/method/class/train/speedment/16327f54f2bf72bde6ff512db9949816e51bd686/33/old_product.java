public MapStream<K, V> filterValue(Predicate<? super V> predicate) {
        return filter(e -> predicate.test(e.getValue()));
    }