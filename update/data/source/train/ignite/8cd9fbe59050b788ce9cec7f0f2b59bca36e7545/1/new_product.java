@Override public void loadCache(IgniteBiInClosure<K, V> clo, Object... args) throws CacheLoaderException {
        if (clo == null)
            return;

        if (args == null || args.length == 0)
            args = new String[] {"select * from " + controller.getPersistenceSettings().getKeyspace() + "." + cassandraTable() + ";"};

        ExecutorService pool = null;

        Collection<Future<?>> futs = new ArrayList<>(args.length);

        try {
            pool = Executors.newFixedThreadPool(maxPoolSize);

            CassandraSession ses = getCassandraSession();

            for (Object obj : args) {
                LoadCacheCustomQueryWorker<K, V> task = null;

                if (obj instanceof Statement)
                    task = new LoadCacheCustomQueryWorker<>(ses, (Statement)obj, controller, log, clo);
                else if (obj instanceof String) {
                    String qry = ((String)obj).trim();

                    if (qry.toLowerCase().startsWith("select"))
                        task = new LoadCacheCustomQueryWorker<>(ses, (String) obj, controller, log, clo);
                }

                if (task != null)
                    futs.add(pool.submit(task));
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