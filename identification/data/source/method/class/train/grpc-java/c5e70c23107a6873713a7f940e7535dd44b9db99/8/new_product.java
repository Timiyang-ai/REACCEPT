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

    deframe(frame, false);
  }