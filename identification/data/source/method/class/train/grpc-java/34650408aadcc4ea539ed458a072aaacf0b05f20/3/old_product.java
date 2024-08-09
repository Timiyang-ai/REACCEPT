private void cancelStream(ChannelHandlerContext ctx, CancelStreamCommand cmd,
      ChannelPromise promise) throws Http2Exception {
    NettyClientStream stream = cmd.stream();
    stream.transportReportStatus(Status.CANCELLED, true, new Metadata.Trailers());

    // No need to set the stream status for a cancellation. It should already have been
    // set prior to sending the command.

    // If the stream hasn't been created yet, remove it from the pending queue.
    if (stream.id() == null) {
      removePendingStream(stream);
      promise.setSuccess();
      return;
    }

    // Send a RST_STREAM frame to terminate this stream. If the stream doesn't exist, assume it is
    // already closed.
    Http2Stream http2Stream = connection().stream(stream.id());
    if (http2Stream != null && http2Stream.state() != Http2Stream.State.CLOSED) {
      // Note: RST_STREAM frames are automatically flushed.
      encoder().writeRstStream(ctx, stream.id(), Http2Error.CANCEL.code(), promise);
    } else {
      // This does allow for a race in the case of two consecutive cancels where the RST_STREAM
      // from the first hasn't completed when we setSuccess here for the second. But we don't care.
      promise.setSuccess();
    }
  }