@Override public void loadCache(IgniteBiInClosure<K, V> clo, Object... args) throws CacheLoaderException {
        if (clo == null || args == null || args.length == 0)
            return;

        ExecutorService pool = null;

        Collection<Future<?>> futs = new ArrayList<>(args.length);

        try {
            pool = Executors.newFixedThreadPool(maxPoolSize);

            CassandraSession ses = getCassandraSession();

            for (Object obj : args) {
                if (obj == null || !(obj instanceof String) || !((String)obj).trim().toLowerCase().startsWith("select"))
                    continue;

                futs.add(pool.submit(new LoadCacheCustomQueryWorker<>(ses, (String) obj, controller, log, clo)));
            }

            for (Future<?> fut : futs)
                U.get(fut);

            if (log != null && log.isDebugEnabled() && storeSes != null)
                log.debug("Cache loaded from db: " + storeSes.cacheName());
        }
        catch (IgniteCheckedException e) {
            if (storeSes != null)
                throw new CacheLoaderException("Failed to load Ignite cache: " + storeSes.cacheName(), e.getCause());
            else
                throw new CacheLoaderException("Failed to load cache", e.getCause());
        }
        finally {
            U.shutdownNow(getClass(), pool, log);
        }
    }