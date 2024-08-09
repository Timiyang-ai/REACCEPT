private void cancelStream(ChannelHandlerContext ctx, CancelClientStreamCommand cmd,
      ChannelPromise promise) {
    NettyClientStream.TransportState stream = cmd.stream();
    PerfMark.startTask("NettyClientHandler.cancelStream", stream.tag());
    cmd.getLink().link();
    try {
      Status reason = cmd.reason();
      if (reason != null) {
        stream.transportReportStatus(reason, true, new Metadata());
      }
      if (!cmd.stream().isNonExistent()) {
        encoder().writeRstStream(ctx, stream.id(), Http2Error.CANCEL.code(), promise);
      } else {
        promise.setSuccess();
      }
    } finally {
      PerfMark.stopTask("NettyClientHandler.cancelStream", stream.tag());
    }
  }