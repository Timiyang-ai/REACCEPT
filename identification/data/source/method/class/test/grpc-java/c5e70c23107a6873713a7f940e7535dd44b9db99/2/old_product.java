protected void inboundDataReceived(Buffer frame) {
    Preconditions.checkNotNull(frame, "frame");
    boolean needToCloseFrame = true;
    try {
      if (inboundPhase() == Phase.STATUS) {
        return;
      }
      if (inboundPhase() == Phase.HEADERS) {
        // Have not received headers yet so error
        inboundTransportError(Status.INTERNAL
            .withDescription("headers not received before payload"));
        return;
      }
      inboundPhase(Phase.MESSAGE);

      needToCloseFrame = false;
      deframe(frame, false);
    } finally {
      if (needToCloseFrame) {
        frame.close();
      }
    }
  }