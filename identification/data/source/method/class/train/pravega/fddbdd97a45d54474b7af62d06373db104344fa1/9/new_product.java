public CompletableFuture<Boolean> sealSegment(final String scope,
                                                  final String stream,
                                                  final int segmentNumber,
                                                  final HostControllerStore hostControllerStore,
                                                  final ConnectionFactory clientCF) {
        final CompletableFuture<Boolean> result = new CompletableFuture<>();
        final NodeUri uri = getSegmentUri(scope, stream, segmentNumber, hostControllerStore);

        final WireCommandType type = WireCommandType.SEAL_SEGMENT;
        final FailingReplyProcessor replyProcessor = new FailingReplyProcessor() {

            @Override
            public void connectionDropped() {
                result.completeExceptionally(
                        new WireCommandFailedException(type, WireCommandFailedException.Reason.ConnectionDropped));
            }

            @Override
            public void wrongHost(WireCommands.WrongHost wrongHost) {
                result.completeExceptionally(
                        new WireCommandFailedException(type, WireCommandFailedException.Reason.UnknownHost));
            }

            @Override
            public void segmentSealed(WireCommands.SegmentSealed segmentSealed) {
                result.complete(true);
            }

            @Override
            public void segmentIsSealed(WireCommands.SegmentIsSealed segmentIsSealed) {
                result.complete(true);
            }
        };

        whenComplete(sendRequestOverNewConnection(
                new WireCommands.SealSegment(Segment.getScopedName(scope, stream, segmentNumber)),
                replyProcessor,
                clientCF,
                ModelHelper.encode(uri)), result);
        return result;
    }