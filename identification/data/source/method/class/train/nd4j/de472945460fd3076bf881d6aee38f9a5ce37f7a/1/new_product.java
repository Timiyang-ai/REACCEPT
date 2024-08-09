public boolean pin(@NonNull VoidAggregation aggregation) {
        RequestDescriptor descriptor = RequestDescriptor.createDescriptor(aggregation.getOriginatorId(), aggregation.getTaskId());
        VoidAggregation existing = clipboard.get(descriptor);
        if (existing == null) {
            existing = aggregation;
            trackingCounter.incrementAndGet();
            clipboard.put(descriptor, aggregation);
        }

        existing.accumulateAggregation(aggregation);

        //if (counter.incrementAndGet() % 10000 == 0)
        //    log.info("Clipboard stats: Totals: {}; Completed: {};", clipboard.size(), completedQueue.size());

        int missing = existing.getMissingChunks();
        if (missing == 0) {
            completedQueue.add(existing);
            completedCounter.incrementAndGet();
            return true;
        } else return false;
    }