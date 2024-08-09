ICompletableFuture<ReadResultSet<E>> readManyAsync(long startSequence, int minCount,
                                                       int maxCount, IFunction<E, Boolean> filter);