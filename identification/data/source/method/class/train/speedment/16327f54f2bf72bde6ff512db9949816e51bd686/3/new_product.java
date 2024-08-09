public boolean allMatch(BiPredicate<? super K, ? super V> predicate) {
        requireNonNull(predicate);
        return inner.allMatch(e -> predicate.test(e.getKey(), e.getValue()));
    }