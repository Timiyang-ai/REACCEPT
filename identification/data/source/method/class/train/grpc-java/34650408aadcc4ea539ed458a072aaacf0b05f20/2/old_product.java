private void cancelStream(ChannelHandlerContext ctx, CancelClientStreamCommand cmd,
      ChannelPromise promise) {
    NettyClientStream stream = cmd.stream();
    stream.transportReportStatus(cmd.reason(), true, new Metadata.Trailers());
    encoder().writeRstStream(ctx, stream.id(), Http2Error.CANCEL.code(), promise);
  }