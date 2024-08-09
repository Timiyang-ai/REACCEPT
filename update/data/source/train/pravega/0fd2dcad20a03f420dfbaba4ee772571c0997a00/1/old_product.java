CompletableFuture<Collection<BucketUpdate>> groupByBucket(Collection<BucketUpdate.KeyUpdate> keyUpdates,
                                                              DirectSegmentAccess segment, TimeoutTimer timer) {
        val updatesByHash = keyUpdates.stream()
                                      .collect(Collectors.groupingBy(k -> this.hasher.hash(k.getKey())));
        return locateBuckets(updatesByHash.keySet(), segment, timer)
                .thenApplyAsync(buckets -> {
                    val result = new HashMap<TableBucket, BucketUpdate>();
                    buckets.forEach((keyHash, bucket) -> {
                        // Add the bucket to the result and record this Key as a "new" key in it.
                        BucketUpdate bu = result.computeIfAbsent(bucket, BucketUpdate::new);
                        updatesByHash.get(keyHash).forEach(bu::withKeyUpdate);
                    });

                    return result.values();
                }, this.executor);
    }