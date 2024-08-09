public static Boolean sealSegment(final String scope,
                                      final String stream,
                                      final int segmentNumber,
                                      final HostControllerStore hostControllerStore,
                                      final ConnectionFactory clientCF,
                                      final ScheduledExecutorService executor) {
        Retry.withExpBackoff(100, 10, Integer.MAX_VALUE, 100000)
                .retryingOn(SealingFailedException.class)
                .throwingOn(RuntimeException.class)
                .runAsync(() -> {
                    NodeUri uri = SegmentHelper.getSegmentUri(scope, stream, segmentNumber, hostControllerStore);
                    return SegmentHelper.sealSegment(scope, stream, segmentNumber, ModelHelper.encode(uri), clientCF);
                }, executor);
        return true;
    }