@Override
  public final void cancel(Status reason) {
    Preconditions.checkArgument(EnumSet.of(CANCELLED, DEADLINE_EXCEEDED).contains(reason.getCode()),
        "Invalid cancellation reason");
    outboundPhase(Phase.STATUS);
    sendCancel(reason);
    dispose();
  }