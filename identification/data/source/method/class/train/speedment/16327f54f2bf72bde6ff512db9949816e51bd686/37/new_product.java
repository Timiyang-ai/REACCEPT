public boolean anyMatch(BiPredicate<? super K, ? super V> predicate) {
        requireNonNull(predicate);
        return inner.anyMatch(e -> predicate.test(e.getKey(), e.getValue()));
    }