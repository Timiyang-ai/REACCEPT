@Subscribe
  public synchronized void vetoed(Vetoed vetoEvent) {
    Set<Veto> vetoes = vetoEvent.getVetoes();
    // Suppress any veto events relating to dedicated host/task mismatches.
    if (!Iterables.any(vetoes, IS_DEDICATED)) {
      record(vetoEvent.getTaskId(), vetoes);
    }
  }