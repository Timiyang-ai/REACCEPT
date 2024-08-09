@Override
  public void cancel() {
    outboundPhase(Phase.STATUS);
    if (id() != null) {
      // Only send a cancellation to remote side if we have actually been allocated
      // a stream id and we are not already closed. i.e. the server side is aware of the stream.
      sendCancel();
    }
    dispose();
  }