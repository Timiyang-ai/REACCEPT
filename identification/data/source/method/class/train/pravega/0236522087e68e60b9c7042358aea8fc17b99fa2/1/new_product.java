public CompletableFuture<Status> createStream(StreamConfiguration streamConfig) {
        String stream = streamConfig.getName();

        return streamStore.createStream(stream, streamConfig)
                .thenApply(result -> {
                    if (result) {
                        streamStore.getActiveSegments(stream)
                                .thenApply(activeSegments -> {
                                    activeSegments
                                            .stream()
                                            .parallel()
                                            .forEach(segment -> notifyNewSegment(streamConfig.getScope(), stream, segment.getNumber()));
                                    return null;
                                });
                        return Status.SUCCESS;
                    } else {
                        return Status.FAILURE;
                    }
                });
    }