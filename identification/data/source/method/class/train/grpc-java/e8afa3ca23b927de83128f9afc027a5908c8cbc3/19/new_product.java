private void createStream(CreateStreamCommand command, final ChannelPromise promise)
          throws Exception {
    if (lifecycleManager.getShutdownThrowable() != null) {
      // The connection is going away, just terminate the stream now.
      promise.setFailure(lifecycleManager.getShutdownThrowable());
      return;
    }

    // Get the stream ID for the new stream.
    final int streamId;
    try {
      streamId = incrementAndGetNextStreamId();
    } catch (StatusException e) {
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

    final NettyClientStream stream = command.stream();
    final Http2Headers headers = command.headers();
    stream.id(streamId);

    // Create an intermediate promise so that we can intercept the failure reported back to the
    // application.
    ChannelPromise tempPromise = ctx().newPromise();
    encoder().writeHeaders(ctx(), streamId, headers, 0, false, tempPromise)
            .addListener(new ChannelFutureListener() {
              @Override
              public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                  // The http2Stream will be null in case a stream buffered in the encoder
                  // was canceled via RST_STREAM.
                  Http2Stream http2Stream = connection().stream(streamId);
                  if (http2Stream != null) {
                    http2Stream.setProperty(streamKey, stream);

                    // Attach the client stream to the HTTP/2 stream object as user data.
                    stream.setHttp2Stream(http2Stream);
                  }
                  // Otherwise, the stream has been cancelled and Netty is sending a
                  // RST_STREAM frame which causes it to purge pending writes from the
                  // flow-controller and delete the http2Stream. The stream listener has already
                  // been notified of cancellation so there is nothing to do.

                  // Just forward on the success status to the original promise.
                  promise.setSuccess();
                } else {
                  final Throwable cause = future.cause();
                  if (cause instanceof StreamBufferingEncoder.Http2GoAwayException) {
                    StreamBufferingEncoder.Http2GoAwayException e =
                        (StreamBufferingEncoder.Http2GoAwayException) cause;
                    lifecycleManager.notifyShutdown(statusFromGoAway(e.errorCode(), e.debugData()));
                    promise.setFailure(lifecycleManager.getShutdownThrowable());
                  } else {
                    promise.setFailure(cause);
                  }
                }
              }
            });
  }