public static <K, V, C extends Serializable, D extends AutoCloseable> D getData(
        Ignite ignite,
        String upstreamCacheName, IgniteBiPredicate<K, V> filter,
        UpstreamTransformerBuilder transformerBuilder,
        String datasetCacheName, UUID datasetId,
        PartitionDataBuilder<K, V, C, D> partDataBuilder,
        LearningEnvironment env) {

        PartitionDataStorage dataStorage = (PartitionDataStorage)ignite
            .cluster()
            .nodeLocalMap()
            .computeIfAbsent(String.format(DATA_STORAGE_KEY_TEMPLATE, datasetId), key -> new PartitionDataStorage());

        final int part = env.partition();

        return dataStorage.computeDataIfAbsent(part, () -> {
            IgniteCache<Integer, C> learningCtxCache = ignite.cache(datasetCacheName);
            C ctx = learningCtxCache.get(part);

            IgniteCache<K, V> upstreamCache = ignite.cache(upstreamCacheName);

            ScanQuery<K, V> qry = new ScanQuery<>();
            qry.setLocal(true);
            qry.setPartition(part);
            qry.setFilter(filter);

            UpstreamTransformer transformer = transformerBuilder.build(env);
            UpstreamTransformer transformerCp = Utils.copy(transformer);

            long cnt = computeCount(upstreamCache, qry, transformer);

            if (cnt > 0) {
                try (QueryCursor<UpstreamEntry<K, V>> cursor = upstreamCache.query(qry,
                    e -> new UpstreamEntry<>(e.getKey(), e.getValue()))) {

                    Iterator<UpstreamEntry<K, V>> it = cursor.iterator();
                    Stream<UpstreamEntry> transformedStream = transformerCp.transform(Utils.asStream(it, cnt).map(x -> (UpstreamEntry)x));
                    it = Utils.asStream(transformedStream.iterator()).map(x -> (UpstreamEntry<K, V>)x).iterator();

                    Iterator<UpstreamEntry<K, V>> iter = new IteratorWithConcurrentModificationChecker<>(it, cnt,
                        "Cache expected to be not modified during dataset data building [partition=" + part + ']');

                    return partDataBuilder.build(env, iter, cnt, ctx);
                }
            }

            return null;
        });
    }