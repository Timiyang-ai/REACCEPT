CompletableFuture<List<Segment>> scale(final String name,
                                           final List<Integer> sealedSegments,
                                           final List<SimpleEntry<Double, Double>> newRanges,
                                           final long scaleTimestamp);