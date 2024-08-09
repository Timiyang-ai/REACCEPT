private void cancelStream(ChannelHandlerContext ctx, CancelStreamCommand cmd,
      ChannelPromise promise) throws Http2Exception {
    NettyClientStream stream = cmd.stream();
    stream.setStatus(Status.CANCELLED, new Metadata.Trailers());

    // No need to set the stream status for a cancellation. It should already have been
    // set prior to sending the command.

    // If the stream hasn't been created yet, remove it from the pending queue.
    if (stream.id() == null) {
      removePendingStream(stream);
      promise.setSuccess();
      return;
    }

    // Send a RST_STREAM frame to terminate this stream.
    Http2Stream http2Stream = connection().requireStream(stream.id());
    if (http2Stream.state() != Http2Stream.State.CLOSED) {
      // Note: RST_STREAM frames are automatically flushed.
      encoder().writeRstStream(ctx, stream.id(), Http2Error.CANCEL.code(), promise);
    }
  }