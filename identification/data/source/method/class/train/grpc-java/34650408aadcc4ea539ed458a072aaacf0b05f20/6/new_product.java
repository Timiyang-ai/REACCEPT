private void cancelStream(ChannelHandlerContext ctx, CancelClientStreamCommand cmd,
      ChannelPromise promise) {
    NettyClientStream.TransportState stream = cmd.stream();
    Status reason = cmd.reason();
    if (reason != null) {
      stream.transportReportStatus(reason, true, new Metadata());
    }
    if (!cmd.stream().isNonExistent()) {
      encoder().writeRstStream(ctx, stream.id(), Http2Error.CANCEL.code(), promise);
    } else {
      promise.setSuccess();
    }
  }