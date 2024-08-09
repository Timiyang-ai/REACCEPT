private void cancelStream(ChannelHandlerContext ctx, CancelStreamCommand cmd,
      ChannelPromise promise) throws Http2Exception {
    NettyClientStream stream = cmd.stream();
    stream.transportReportStatus(Status.CANCELLED, true, new Metadata.Trailers());
    encoder().writeRstStream(ctx, stream.id(), Http2Error.CANCEL.code(), promise);
  }