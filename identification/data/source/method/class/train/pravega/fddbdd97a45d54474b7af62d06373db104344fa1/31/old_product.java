public static Boolean sealSegment(final String scope,
                                      final String stream,
                                      final int segmentNumber,
                                      final HostControllerStore hostControllerStore,
                                      final ConnectionFactory clientCF) {
        Retry.withExpBackoff(100, 10, Integer.MAX_VALUE, 100000)
                .retryingOn(SealingFailedException.class)
                .throwingOn(RuntimeException.class)
                .run(() -> {
                    NodeUri uri = SegmentHelper.getSegmentUri(scope, stream, segmentNumber, hostControllerStore);
                    return FutureHelpers.<Boolean, SealingFailedException>getAndHandleExceptions(
                            SegmentHelper.sealSegment(scope, stream, segmentNumber, ModelHelper.encode(uri), clientCF),
                            SealingFailedException::new);
                });
        return true;
    }