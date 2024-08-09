@SuppressWarnings("unchecked")
    protected static <K> void loadAll(Cache<K, ?> cache, final Set<K> keys, final boolean replaceExistingValues)
        throws Exception {
        IgniteCache<K, Object> cacheCp = (IgniteCache<K, Object>)cache;

        GridAbstractTest.executeOnLocalOrRemoteJvm(cacheCp, new TestCacheRunnable<K, Object>() {
            private static final long serialVersionUID = -3030833765012500545L;

            @Override public void run(Ignite ignite, IgniteCache<K, Object> cache) throws Exception {
                final AtomicReference<Exception> ex = new AtomicReference<>();

                final CountDownLatch latch = new CountDownLatch(1);

                cache.loadAll(keys, replaceExistingValues, new CompletionListener() {
                    @Override public void onCompletion() {
                        latch.countDown();
                    }

                    @Override public void onException(Exception e) {
                        ex.set(e);

                        latch.countDown();
                    }
                });

                latch.await();

                if (ex.get() != null)
                    throw ex.get();
            }
        });
    }