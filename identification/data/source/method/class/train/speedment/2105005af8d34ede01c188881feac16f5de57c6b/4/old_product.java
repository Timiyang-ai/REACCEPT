public MapStream<K, V> distinctValues(BinaryOperator<K> merger) {
        return MapStream.flip(MapStream.flip(this).distinctKeys(merger));
    }