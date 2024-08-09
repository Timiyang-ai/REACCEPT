@Override
    public boolean anyMatch(Predicate<? super Map.Entry<K, V>> predicate) {
        return inner.anyMatch(requireNonNull(predicate));
    }