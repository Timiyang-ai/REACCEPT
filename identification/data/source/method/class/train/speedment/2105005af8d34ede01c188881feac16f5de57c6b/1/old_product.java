public MapStream<K, V> distinctKeys(BinaryOperator<V> merger) {
        final Map<K, V> result = new ConcurrentHashMap<>();
        
        inner.forEach(e -> {
            result.compute(e.getKey(), (k, v) -> 
                merger.apply(e.getValue(), v)
            );
        });
        
        return MapStream.of(result);
    }