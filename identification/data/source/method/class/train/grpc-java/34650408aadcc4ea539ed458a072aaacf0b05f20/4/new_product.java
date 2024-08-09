private void cancelStream(ChannelHandlerContext ctx, CancelClientStreamCommand cmd,
      ChannelPromise promise) {
    NettyClientStream.TransportState stream = cmd.stream();
    Status reason = cmd.reason();
    if (reason != null) {
      stream.transportReportStatus(reason, true, new Metadata());
    }
    encoder().writeRstStream(ctx, stream.id(), Http2Error.CANCEL.code(), promise);
  }