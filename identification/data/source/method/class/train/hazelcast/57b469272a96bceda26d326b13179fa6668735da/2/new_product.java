ICompletableFuture<ReadResultSet<E>> readManyAsync(long startSequence, int minCount, int maxCount,
                                                       @Nullable IFunction<E, Boolean> filter);