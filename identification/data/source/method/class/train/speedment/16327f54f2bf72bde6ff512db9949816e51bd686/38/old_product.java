public boolean noneMatch(BiPredicate<? super K, ? super V> predicate) {
        return inner.noneMatch(e -> predicate.test(e.getKey(), e.getValue()));
    }