public Map<K, V> toConcurrentMap(BinaryOperator<V> mergeFunction) {
        requireNonNull(mergeFunction);
        return inner.collect(Collectors.toConcurrentMap(
            Map.Entry::getKey,
            Map.Entry::getValue,
            mergeFunction
        ));
    }