public MapStream<K, V> distinctKeys(BinaryOperator<V> merger) {
        final boolean parallel = isParallel();
        final Map<K, V> result = parallel
            ? Collections.synchronizedMap(new LinkedHashMap<>())
            : new LinkedHashMap<>();
        
        inner.forEachOrdered(e ->
            result.compute(e.getKey(), (k, v) -> 
                v == null 
                    ? e.getValue()
                    : merger.apply(e.getValue(), v)
            ));
        
        return MapStream.of(result, parallel);
    }