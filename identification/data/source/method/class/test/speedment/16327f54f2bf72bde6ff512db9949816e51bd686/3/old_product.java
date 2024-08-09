@Override
    public boolean noneMatch(Predicate<? super Map.Entry<K, V>> predicate) {
        return inner.noneMatch(predicate);
    }