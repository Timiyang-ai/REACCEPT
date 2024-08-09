public static <K, V, C extends Serializable> void initContext(
        Ignite ignite,
        String upstreamCacheName,
        UpstreamTransformerBuilder transformerBuilder,
        IgniteBiPredicate<K, V> filter,
        String datasetCacheName,
        PartitionContextBuilder<K, V, C> ctxBuilder,
        LearningEnvironmentBuilder envBuilder,
        int retries,
        int interval,
        boolean isKeepBinary) {
        affinityCallWithRetries(ignite, Arrays.asList(datasetCacheName, upstreamCacheName), part -> {
            Ignite locIgnite = Ignition.localIgnite();
            LearningEnvironment env = envBuilder.buildForWorker(part);

            IgniteCache<K, V> locUpstreamCache = locIgnite.cache(upstreamCacheName);

            if (isKeepBinary)
                locUpstreamCache = locUpstreamCache.withKeepBinary();

            ScanQuery<K, V> qry = new ScanQuery<>();
            qry.setLocal(true);
            qry.setPartition(part);
            qry.setFilter(filter);

            C ctx;
            UpstreamTransformer transformer = transformerBuilder.build(env);
            UpstreamTransformer transformerCp = Utils.copy(transformer);

            long cnt = computeCount(locUpstreamCache, qry, transformer);

            try (QueryCursor<UpstreamEntry<K, V>> cursor = locUpstreamCache.query(qry,
                e -> new UpstreamEntry<>(e.getKey(), e.getValue()))) {

                Iterator<UpstreamEntry<K, V>> it = cursor.iterator();
                Stream<UpstreamEntry> transformedStream = transformerCp.transform(Utils.asStream(it, cnt).map(x -> (UpstreamEntry)x));
                it = Utils.asStream(transformedStream.iterator()).map(x -> (UpstreamEntry<K, V>)x).iterator();

                Iterator<UpstreamEntry<K, V>> iter = new IteratorWithConcurrentModificationChecker<>(
                    it,
                    cnt,
                    "Cache expected to be not modified during dataset data building [partition=" + part + ']');

                ctx = ctxBuilder.build(env, iter, cnt);
            }

            IgniteCache<Integer, C> datasetCache = locIgnite.cache(datasetCacheName);

            datasetCache.put(part, ctx);

            return part;
        }, retries, interval);
    }