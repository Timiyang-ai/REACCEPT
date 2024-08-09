CompletableFuture<List<Segment>> scale(final String scopeName,
                                           final String streamName,
                                           final List<Integer> sealedSegments,
                                           final List<SimpleEntry<Double, Double>> newRanges,
                                           final long scaleTimestamp);