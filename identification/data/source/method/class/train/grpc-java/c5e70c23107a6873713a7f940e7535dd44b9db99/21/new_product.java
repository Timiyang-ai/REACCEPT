@Override
  public void cancel() {
    outboundPhase(Phase.STATUS);
    sendCancel();
    dispose();
  }