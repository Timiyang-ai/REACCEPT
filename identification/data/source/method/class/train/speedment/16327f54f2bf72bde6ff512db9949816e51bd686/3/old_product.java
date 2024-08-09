public boolean allMatch(BiPredicate<? super K, ? super V> predicate) {
        return inner.allMatch(e -> predicate.test(e.getKey(), e.getValue()));
    }