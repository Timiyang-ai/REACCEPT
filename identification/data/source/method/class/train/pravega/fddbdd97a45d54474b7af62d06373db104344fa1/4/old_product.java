public CompletableFuture<Boolean> sealSegment(final String scope,
                                                  final String stream,
                                                  final long segmentId,
                                                  final HostControllerStore hostControllerStore,
                                                  final ConnectionFactory clientCF,
                                                  String delegationToken,
                                                  final long clientRequestId) {
        final Controller.NodeUri uri = getSegmentUri(scope, stream, segmentId, hostControllerStore);
        final String qualifiedName = getQualifiedStreamSegmentName(scope, stream, segmentId);
        final long requestId = (clientRequestId == RequestTag.NON_EXISTENT_ID) ? idGenerator.get() : clientRequestId;
        return sealSegment(qualifiedName, uri, clientCF, delegationToken, requestId);
    }