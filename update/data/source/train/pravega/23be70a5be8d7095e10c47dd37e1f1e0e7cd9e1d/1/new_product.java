CompletableFuture<Collection<BucketUpdate.Builder>> groupByBucket(DirectSegmentAccess segment, Collection<BucketUpdate.KeyUpdate> keyUpdates,
                                                              TimeoutTimer timer) {
        val updatesByHash = keyUpdates.stream()
                                      .collect(Collectors.groupingBy(k -> this.hasher.hash(k.getKey())));
        return locateBuckets(segment, updatesByHash.keySet(), timer)
                .thenApplyAsync(buckets -> {
                    val result = new HashMap<TableBucket, BucketUpdate.Builder>();
                    buckets.forEach((keyHash, bucket) -> {
                        // Add the bucket to the result and record this Key as a "new" key in it.
                        BucketUpdate.Builder bu = result.computeIfAbsent(bucket, BucketUpdate::forBucket);
                        updatesByHash.get(keyHash).forEach(bu::withKeyUpdate);
                    });

                    return result.values();
                }, this.executor);
    }