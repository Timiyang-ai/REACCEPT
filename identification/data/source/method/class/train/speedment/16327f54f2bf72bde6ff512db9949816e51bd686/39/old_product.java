public Map<K, V> toMap(BinaryOperator<V> mergeFunction) {
        return inner.collect(Collectors.toMap(
            Map.Entry::getKey,
            Map.Entry::getValue,
            mergeFunction
        ));
    }