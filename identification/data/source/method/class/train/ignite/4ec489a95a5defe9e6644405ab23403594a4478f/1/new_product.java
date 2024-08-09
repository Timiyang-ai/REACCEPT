public DistributedMetaStorageKeyValuePair[] localFullData() {
        return cache.entrySet().stream().map(
            entry -> new DistributedMetaStorageKeyValuePair(entry.getKey(), entry.getValue())
        ).toArray(DistributedMetaStorageKeyValuePair[]::new);
    }