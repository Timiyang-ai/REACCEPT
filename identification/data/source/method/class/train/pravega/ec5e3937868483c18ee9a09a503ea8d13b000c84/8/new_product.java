CompletableFuture<List<Segment>> startScale(final String scope, final String name,
                                                final List<Integer> sealedSegments,
                                                final List<SimpleEntry<Double, Double>> newRanges,
                                                final long scaleTimestamp,
                                                final OperationContext context,
                                                final Executor executor);