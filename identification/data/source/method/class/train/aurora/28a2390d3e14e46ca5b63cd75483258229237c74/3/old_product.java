@Subscribe
  public synchronized void vetoed(Vetoed vetoEvent) {
    Preconditions.checkNotNull(vetoEvent);
    fitByTask.getUnchecked(vetoEvent.getTaskId()).maybeUpdate(vetoEvent.getVetoes());
  }