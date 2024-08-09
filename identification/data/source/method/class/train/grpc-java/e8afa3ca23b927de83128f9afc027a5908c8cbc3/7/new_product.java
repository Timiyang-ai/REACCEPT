private void createStream(CreateStreamCommand command, final ChannelPromise promise)
          throws Exception {
    // Get the stream ID for the new stream.
    final int streamId;
    try {
      streamId = getAndIncrementNextStreamId();
    } catch (StatusException e) {
      // Stream IDs have been exhausted for this connection. Fail the promise immediately.
      promise.setFailure(e);

      // Initiate a graceful shutdown if we haven't already.
      if (!connection().goAwaySent()) {
        logger.fine("Stream IDs have been exhausted for this connection. "
                + "Initiating graceful shutdown of the connection.");
        super.close(ctx(), ctx().newPromise());
      }
      return;
    }

    final NettyClientStream stream = command.stream();
    final Http2Headers headers = command.headers();
    stream.id(streamId);
    encoder().writeHeaders(ctx(), streamId, headers, 0, false, promise)
            .addListener(new ChannelFutureListener() {
              @Override
              public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                  // The http2Stream will be null in case a stream buffered in the encoder
                  // was canceled via RST_STREAM.
                  Http2Stream http2Stream = connection().stream(streamId);
                  if (http2Stream != null) {
                    http2Stream.setProperty(streamKey, stream);
                  }
                  // Attach the client stream to the HTTP/2 stream object as user data.
                  stream.setHttp2Stream(http2Stream);
                } else {
                  if (future.cause() instanceof GoAwayClosedStreamException) {
                    GoAwayClosedStreamException e = (GoAwayClosedStreamException) future.cause();
                    goAwayStatus(statusFromGoAway(e.errorCode(), e.debugData()));
                    stream.transportReportStatus(goAwayStatus, false, new Metadata());
                  } else {
                    stream.transportReportStatus(Status.fromThrowable(future.cause()), true,
                        new Metadata());
                  }
                }
              }
            });
  }