@Override
    public Map.Entry<K, V> reduce(Map.Entry<K, V> identity, BinaryOperator<Map.Entry<K, V>> accumulator) {
        return inner.reduce(requireNonNull(identity), requireNonNull(accumulator));
    }