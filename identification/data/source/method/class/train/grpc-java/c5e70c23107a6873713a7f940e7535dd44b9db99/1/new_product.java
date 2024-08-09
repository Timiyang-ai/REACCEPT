@Override
  public final void cancel(Status reason) {
    checkArgument(CANCEL_REASONS.contains(reason.getCode()), "Invalid cancellation reason");
    outboundPhase(Phase.STATUS);
    sendCancel(reason);
    dispose();
  }