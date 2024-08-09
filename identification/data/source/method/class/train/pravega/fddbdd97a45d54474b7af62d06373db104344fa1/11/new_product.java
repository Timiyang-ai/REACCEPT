public CompletableFuture<Void> sealSegment(final String scope,
                                                  final String stream,
                                                  final long segmentId,
                                                  String delegationToken,
                                                  final long clientRequestId) {
        final Controller.NodeUri uri = getSegmentUri(scope, stream, segmentId);
        final String qualifiedName = getQualifiedStreamSegmentName(scope, stream, segmentId);
        final WireCommandType type = WireCommandType.SEAL_SEGMENT;
        RawClient connection = new RawClient(ModelHelper.encode(uri), connectionFactory);
        final long requestId = connection.getFlow().asLong();

        return sendRequest(connection, requestId, new WireCommands.SealSegment(requestId, qualifiedName, delegationToken))
                .thenAccept(r -> handleReply(clientRequestId, r, connection, qualifiedName, WireCommands.SealSegment.class, type));
    }