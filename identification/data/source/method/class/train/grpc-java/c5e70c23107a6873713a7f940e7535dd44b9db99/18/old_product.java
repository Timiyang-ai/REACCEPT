protected void inboundDataReceived(Buffer frame) {
    Preconditions.checkNotNull(frame, "frame");
    if (state() == CLOSED) {
      frame.close();
      return;
    }
    if (inboundPhase == Phase.HEADERS) {
      // Have not received headers yet so error
      inboundTransportError(Status.INTERNAL.withDescription("headers not received before payload"));
      frame.close();
      return;
    }
    inboundPhase(Phase.MESSAGE);
    if (!GRPC_V2_PROTOCOL) {
      deframer.deframe(frame, false);
    } else {
      deframer2.deframe(frame, false);
    }
  }