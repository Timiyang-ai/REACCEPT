@Override
    public Optional<Map.Entry<K, V>> reduce(BinaryOperator<Map.Entry<K, V>> accumulator) {
        return inner.reduce(requireNonNull(accumulator));
    }