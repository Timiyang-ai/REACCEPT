public State startTransaction(long txn_id, int base_partition, Procedure catalog_proc, Object args[]) {
        assert (catalog_proc != null);
        long start_time = System.currentTimeMillis();
        if (d) LOG.debug(String.format("Starting estimation for new %s [partition=%d]",
                                       TransactionState.formatTxnName(catalog_proc, txn_id), base_partition));

        // If we don't have a graph for this procedure, we should probably just return null
        // This will be the case for all sysprocs
        if (this.markovs == null) return (null);
        MarkovGraph markov = this.markovs.getFromParams(txn_id, base_partition, args, catalog_proc);
        if (markov == null) {
            if (d) LOG.debug("No MarkovGraph is available for " + TransactionState.formatTxnName(catalog_proc, txn_id));
            return (null);
        }
        
        Vertex start = markov.getStartVertex();
        assert(start != null) : "The start vertex is null. This should never happen!";
        MarkovPathEstimator estimator = null;
        
        // We'll reuse the last MarkovPathEstimator (and it's path) if the graph has been accurate for
        // other previous transactions. This prevents us from having to recompute the path every single time,
        // especially for single-partition transactions where the clustered MarkovGraphs are accurate
        if (hstore_conf.site.markov_path_caching && markov.getAccuracyRatio() >= hstore_conf.site.markov_path_caching_threshold) {
            estimator = this.cached_estimators.get(markov);
        }
            
        // Otherwise we have to recalculate everything from scatch again
        if (estimator == null) {
            if (d) LOG.debug("Recalculating initial path estimate for " + TransactionState.formatTxnName(catalog_proc, txn_id)); 
            try {
                estimator = (MarkovPathEstimator)ESTIMATOR_POOL.borrowObject();
                estimator.init(markov, this, base_partition, args);
                estimator.enableForceTraversal(true);
            } catch (Exception ex) {
                LOG.error("Failed to intiialize new MarkovPathEstimator for " + TransactionState.formatTxnName(catalog_proc, txn_id));
                throw new RuntimeException(ex);
            }
            
            // Calculate initial path estimate
            if (t) LOG.trace("Estimating initial execution path for " + TransactionState.formatTxnName(catalog_proc, txn_id));
            start.addInstanceTime(txn_id, start_time);
            synchronized (markov) {
                try {
                    estimator.traverse(start);
                    // if (catalog_proc.getName().equalsIgnoreCase("NewBid")) throw new Exception ("Fake!");
                } catch (Throwable e) {
                    try {
                        GraphvizExport<Vertex, Edge> gv = MarkovUtil.exportGraphviz(markov, true, markov.getPath(estimator.getVisitPath()));
                        LOG.error("GRAPH #" + markov.getGraphId() + " DUMP: " + gv.writeToTempFile(catalog_proc));
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    throw new RuntimeException("Failed to estimate path for " + TransactionState.formatTxnName(catalog_proc, txn_id), e);
                }
            } // SYNCH
        } else {
            if (d) LOG.info(String.format("Using cached MarkovPathEstimator for %s [hashCode=%d, ratio=%.02f]",
                                          TransactionState.formatTxnName(catalog_proc, txn_id), estimator.getEstimate().hashCode(), markov.getAccuracyRatio()));
            assert(estimator.isCached()) :
                String.format("The cached MarkovPathEstimator used by %s does not have its cached flag set [hashCode=%d]",
                              TransactionState.formatTxnName(catalog_proc, txn_id), estimator.hashCode());
            assert(estimator.getEstimate().isValid()) :
                String.format("Invalid MarkovEstimate for cache Estimator used by %s [hashCode=%d]",
                              TransactionState.formatTxnName(catalog_proc, txn_id), estimator.getEstimate().hashCode());
            estimator.getEstimate().incrementReusedCounter();
        }
        assert(estimator != null);
        if (t) {
            List<Vertex> path = estimator.getVisitPath();
            LOG.trace(String.format("Estimated Path for %s [length=%d]\n%s",
                                    TransactionState.formatTxnName(catalog_proc, txn_id), path.size(),
                                    StringUtil.join("\n----------------------\n", path, "debug")));
            LOG.trace(String.format("MarkovEstimate for %s\n%s", TransactionState.formatTxnName(catalog_proc, txn_id), estimator.getEstimate()));
        }
        
        if (d) LOG.debug(String.format("Creating new State %s [touchedPartitions=%s]", TransactionState.formatTxnName(catalog_proc, txn_id), estimator.getTouchedPartitions()));
        State state = null;
        try {
            state = (State)STATE_POOL.borrowObject();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        // Calling init() will set the initial MarkovEstimate for the State
        state.init(txn_id, base_partition, markov, estimator, start_time);
        State old = this.txn_states.put(txn_id, state);
        assert(old == null) : "Duplicate transaction id " + TransactionState.formatTxnName(catalog_proc, txn_id);

        this.txn_count.incrementAndGet();
        return (state);
    }