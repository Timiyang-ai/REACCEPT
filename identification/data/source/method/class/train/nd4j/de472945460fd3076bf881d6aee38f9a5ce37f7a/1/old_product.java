public boolean pin(@NonNull VoidAggregation aggregation) {
        VoidAggregation existing = clipboard.get(aggregation.getTaskId());
        if (existing == null) {
            existing = aggregation;
            trackingCounter.incrementAndGet();
            clipboard.put(aggregation.getTaskId(), aggregation);
        }

        existing.accumulateAggregation(aggregation);

        //if (counter.incrementAndGet() % 10000 == 0)
        //    log.info("Clipboard stats: Totals: {}; Completed: {};", clipboard.size(), completedQueue.size());

        int missing = existing.getMissingChunks();
        if (missing == 0) {
            completedQueue.add(existing);
            completedCounter.incrementAndGet();

            // TODO: delete it from tracking table probably?

            return true;
        } else return false;
    }