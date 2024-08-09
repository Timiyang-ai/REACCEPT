@Override
    protected MultiBackgroundInitializerResults initialize() throws Exception {
        Map<String, BackgroundInitializer<?>> inits;
        synchronized (this) {
            // create a snapshot to operate on
            inits = new HashMap<String, BackgroundInitializer<?>>(
                    childInitializers);
        }

        // start the child initializers
        final ExecutorService exec = getActiveExecutor();
        for (final BackgroundInitializer<?> bi : inits.values()) {
            if (bi.getExternalExecutor() == null) {
                // share the executor service if necessary
                bi.setExternalExecutor(exec);
            }
            bi.start();
        }

        // collect the results
        final Map<String, Object> results = new HashMap<String, Object>();
        final Map<String, ConcurrentException> excepts = new HashMap<String, ConcurrentException>();
        for (final Map.Entry<String, BackgroundInitializer<?>> e : inits.entrySet()) {
            try {
                results.put(e.getKey(), e.getValue().get());
            } catch (final ConcurrentException cex) {
                excepts.put(e.getKey(), cex);
            }
        }

        return new MultiBackgroundInitializerResults(inits, results, excepts);
    }