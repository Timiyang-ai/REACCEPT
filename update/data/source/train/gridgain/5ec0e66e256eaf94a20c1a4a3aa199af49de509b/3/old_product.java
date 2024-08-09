public static <K, V, C extends Serializable> void initContext(Ignite ignite, String upstreamCacheName,
        IgniteBiPredicate<K, V> filter, String datasetCacheName, PartitionContextBuilder<K, V, C> ctxBuilder, int retries,
        int interval) {
        affinityCallWithRetries(ignite, Arrays.asList(datasetCacheName, upstreamCacheName), part -> {
            Ignite locIgnite = Ignition.localIgnite();

            IgniteCache<K, V> locUpstreamCache = locIgnite.cache(upstreamCacheName);

            ScanQuery<K, V> qry = new ScanQuery<>();
            qry.setLocal(true);
            qry.setPartition(part);
            qry.setFilter(filter);

            long cnt = computeCount(locUpstreamCache, qry);

            C ctx;
            try (QueryCursor<UpstreamEntry<K, V>> cursor = locUpstreamCache.query(qry,
                e -> new UpstreamEntry<>(e.getKey(), e.getValue()))) {

                Iterator<UpstreamEntry<K, V>> iter = new IteratorWithConcurrentModificationChecker<>(cursor.iterator(), cnt,
                    "Cache expected to be not modified during dataset data building [partition=" + part + ']');

                ctx = ctxBuilder.build(iter, cnt);
            }

            IgniteCache<Integer, C> datasetCache = locIgnite.cache(datasetCacheName);

            datasetCache.put(part, ctx);

            return part;
        }, retries, interval);
    }