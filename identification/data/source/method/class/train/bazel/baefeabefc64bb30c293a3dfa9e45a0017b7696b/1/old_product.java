public synchronized @GroupedList.Compressed Object getCompressedDirectDepsForDoneEntry() {
    assertKeepDeps();
    Preconditions.checkState(isDone(), "no deps until done. NodeEntry: %s", this);
    return Preconditions.checkNotNull(directDeps, "deps can't be null: %s", this);
  }