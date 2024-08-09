CompletableFuture<Boolean> createStream(final String scopeName,
                                            final String streamName,
                                            final StreamConfiguration configuration,
                                            final long createTimestamp,
                                            final OperationContext context,
                                            final Executor executor);