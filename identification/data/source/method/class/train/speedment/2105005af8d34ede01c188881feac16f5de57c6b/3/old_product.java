public MapStream<K, V> distinctValues(BinaryOperator<K> merger) {
        final Map<V, K> result = new ConcurrentHashMap<>();
        
        inner.forEach(e -> {
            result.compute(e.getValue(), (v, k) -> 
                merger.apply(e.getKey(), k)
            );
        });
        
        return MapStream.flip(MapStream.of(result));
    }