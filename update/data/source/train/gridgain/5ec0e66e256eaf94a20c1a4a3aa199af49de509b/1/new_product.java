public static <K, V, C extends Serializable, D extends AutoCloseable> D getData(
        Ignite ignite,
        String upstreamCacheName, IgniteBiPredicate<K, V> filter,
        UpstreamTransformerChain<K, V> transformersChain,
        String datasetCacheName,
        UUID datasetId,
        int part,
        PartitionDataBuilder<K, V, C, D> partDataBuilder) {

        PartitionDataStorage dataStorage = (PartitionDataStorage)ignite
            .cluster()
            .nodeLocalMap()
            .computeIfAbsent(String.format(DATA_STORAGE_KEY_TEMPLATE, datasetId), key -> new PartitionDataStorage());

        return dataStorage.computeDataIfAbsent(part, () -> {
            IgniteCache<Integer, C> learningCtxCache = ignite.cache(datasetCacheName);
            C ctx = learningCtxCache.get(part);

            IgniteCache<K, V> upstreamCache = ignite.cache(upstreamCacheName);

            ScanQuery<K, V> qry = new ScanQuery<>();
            qry.setLocal(true);
            qry.setPartition(part);
            qry.setFilter(filter);

            UpstreamTransformerChain<K, V> chainCopy = Utils.copy(transformersChain);
            chainCopy.modifySeed(s -> s + part);

            long cnt = computeCount(upstreamCache, qry, chainCopy);

            if (cnt > 0) {
                try (QueryCursor<UpstreamEntry<K, V>> cursor = upstreamCache.query(qry,
                    e -> new UpstreamEntry<>(e.getKey(), e.getValue()))) {

                    Iterator<UpstreamEntry<K, V>> it = cursor.iterator();
                    if (!chainCopy.isEmpty()) {
                        Stream<UpstreamEntry<K, V>> transformedStream = chainCopy.transform(Utils.asStream(it, cnt));
                        it = transformedStream.iterator();
                    }

                    Iterator<UpstreamEntry<K, V>> iter = new IteratorWithConcurrentModificationChecker<>(it, cnt,
                        "Cache expected to be not modified during dataset data building [partition=" + part + ']');

                    return partDataBuilder.build(iter, cnt, ctx);
                }
            }

            return null;
        });
    }