@Override
    public V getAndPut(K key, ValueProvider<? super K, ? extends V> valueProvider) {
        return map.computeIfAbsent(key, k -> valueProvider.provide(k));
    }