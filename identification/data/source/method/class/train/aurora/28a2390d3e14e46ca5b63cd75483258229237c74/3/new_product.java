@Subscribe
  public synchronized void vetoed(Vetoed vetoEvent) {
    Objects.requireNonNull(vetoEvent);
    fitByTask.getUnchecked(vetoEvent.getTaskId()).maybeUpdate(vetoEvent.getVetoes());
  }