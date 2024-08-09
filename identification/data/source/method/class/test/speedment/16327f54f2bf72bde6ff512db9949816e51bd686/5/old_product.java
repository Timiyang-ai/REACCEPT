@Override
    public boolean allMatch(Predicate<? super Map.Entry<K, V>> predicate) {
        return inner.allMatch(predicate);
    }