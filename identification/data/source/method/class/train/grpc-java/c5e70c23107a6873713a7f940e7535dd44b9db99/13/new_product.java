@Override
  public final void cancel(Status reason) {
    checkArgument(!reason.isOk(), "Should not cancel with OK status");
    cancelled = true;
    sendCancel(reason);
    dispose();
  }