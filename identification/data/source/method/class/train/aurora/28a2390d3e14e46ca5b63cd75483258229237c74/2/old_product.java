@Subscribe
  public synchronized void vetoed(Vetoed vetoEvent) {
    Objects.requireNonNull(vetoEvent);
    fitByGroupKey.getUnchecked(vetoEvent.getGroupKey()).maybeUpdate(vetoEvent.getVetoes());
  }