public CompletableFuture<Boolean> sealSegment(final String scope,
                                                  final String stream,
                                                  final int segmentNumber,
                                                  final HostControllerStore hostControllerStore,
                                                  final ConnectionFactory clientCF) {
        final Controller.NodeUri uri = getSegmentUri(scope, stream, segmentNumber, hostControllerStore);
        final CompletableFuture<Boolean> result = new CompletableFuture<>();
        final WireCommandType type = WireCommandType.SEAL_SEGMENT;
        final FailingReplyProcessor replyProcessor = new FailingReplyProcessor() {

            @Override
            public void connectionDropped() {
                log.warn("sealSegment {}/{}/{} connectionDropped", scope, stream, segmentNumber);
                result.completeExceptionally(
                        new WireCommandFailedException(type, WireCommandFailedException.Reason.ConnectionDropped));
            }

            @Override
            public void wrongHost(WireCommands.WrongHost wrongHost) {
                log.warn("sealSegment {}/{}/{} wrongHost", scope, stream, segmentNumber);
                result.completeExceptionally(
                        new WireCommandFailedException(type, WireCommandFailedException.Reason.UnknownHost));
            }

            @Override
            public void segmentSealed(WireCommands.SegmentSealed segmentSealed) {
                log.info("sealSegment {}/{}/{} segmentSealed", scope, stream, segmentNumber);
                result.complete(true);
            }

            @Override
            public void segmentIsSealed(WireCommands.SegmentIsSealed segmentIsSealed) {
                log.info("sealSegment {}/{}/{} SegmentIsSealed", scope, stream, segmentNumber);
                result.complete(true);
            }

            @Override
            public void processingFailure(Exception error) {
                log.error("sealSegment {}/{}/{} failed", scope, stream, segmentNumber, error);
                result.completeExceptionally(error);
            }
        };

        WireCommands.SealSegment request = new WireCommands.SealSegment(idGenerator.get(), 
                Segment.getScopedName(scope, stream, segmentNumber));
        sendRequestAsync(request, replyProcessor, result, clientCF, ModelHelper.encode(uri));
        return result;
    }