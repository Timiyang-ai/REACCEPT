public void createTestStream(final String streamName, final int numSegments)
            throws Exception {
        Preconditions.checkState(this.started.get(), "Services not yet started");
        Preconditions.checkNotNull(streamName);
        Preconditions.checkArgument(numSegments > 0);

        @Cleanup
        StreamManager streamManager = StreamManager.create(clientConfig);
        streamManager.createScope(scope);
        streamManager.createStream(scope, streamName,
                                   StreamConfiguration.builder()
                                                      .scope(scope)
                                                      .streamName(streamName)
                                                      .scalingPolicy(ScalingPolicy.fixed(numSegments))
                                                      .build());
        log.info("Created stream: " + streamName);
    }