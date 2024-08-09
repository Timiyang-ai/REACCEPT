private void createStream(CreateStreamCommand command, ChannelPromise promise)
          throws Exception {
    if (lifecycleManager.getShutdownThrowable() != null) {
      command.stream().setNonExistent();
      // The connection is going away (it is really the GOAWAY case),
      // just terminate the stream now.
      command.stream().transportReportStatus(
          lifecycleManager.getShutdownStatus(), RpcProgress.REFUSED, true, new Metadata());
      promise.setFailure(lifecycleManager.getShutdownThrowable());
      return;
    }

    // Get the stream ID for the new stream.
    int streamId;
    try {
      streamId = incrementAndGetNextStreamId();
    } catch (StatusException e) {
      command.stream().setNonExistent();
      // Stream IDs have been exhausted for this connection. Fail the promise immediately.
      promise.setFailure(e);

      // Initiate a graceful shutdown if we haven't already.
      if (!connection().goAwaySent()) {
        logger.fine("Stream IDs have been exhausted for this connection. "
                + "Initiating graceful shutdown of the connection.");
        lifecycleManager.notifyShutdown(e.getStatus());
        close(ctx(), ctx().newPromise());
      }
      return;
    }

    NettyClientStream.TransportState stream = command.stream();
    Http2Headers headers = command.headers();
    stream.setId(streamId);

    PerfMark.startTask("NettyClientHandler.createStream", stream.tag());
    PerfMark.linkIn(command.getLink());
    try {
      createStreamTraced(
          streamId, stream, headers, command.isGet(), command.shouldBeCountedForInUse(), promise);
    } finally {
      PerfMark.stopTask("NettyClientHandler.createStream", stream.tag());
    }
  }