private void createStream(CreateStreamCommand command, final ChannelPromise promise) {
    final int streamId = getAndIncrementNextStreamId();
    final NettyClientStream stream = command.stream();
    final Http2Headers headers = command.headers();
    // TODO: Send GO_AWAY if streamId overflows
    stream.id(streamId);
    encoder().writeHeaders(ctx, streamId, headers, 0, false, promise)
            .addListener(new ChannelFutureListener() {
              @Override
              public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                  // Attach the client stream to the HTTP/2 stream object as user data.
                  Http2Stream http2Stream = connection().stream(streamId);
                  stream.setHttp2Stream(http2Stream);
                  // The http2Stream will be null in case a stream buffered in the encoder
                  // was canceled via RST_STREAM.
                  if (http2Stream != null) {
                    http2Stream.setProperty(streamKey, stream);
                  }
                } else {
                  if (future.cause() instanceof GoAwayClosedStreamException) {
                    GoAwayClosedStreamException e = (GoAwayClosedStreamException) future.cause();
                    goAwayStatus(statusFromGoAway(e.errorCode(), e.debugData()));
                    stream.transportReportStatus(goAwayStatus, false, new Metadata.Trailers());
                  } else {
                    stream.transportReportStatus(Status.fromThrowable(future.cause()), true,
                        new Metadata.Trailers());
                  }
                }
              }
            });
  }