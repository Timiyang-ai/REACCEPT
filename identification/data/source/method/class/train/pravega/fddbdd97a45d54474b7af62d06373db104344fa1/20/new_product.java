public static Boolean sealSegment(String scope, String stream, int segmentNumber, HostControllerStore hostControllerStore, ConnectionFactory clientCF) {
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