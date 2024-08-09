public final synchronized @GroupedList.Compressed Object getCompressedDirectDepsForDoneEntry() {
    assertKeepDeps();
    Preconditions.checkState(isDone(), "no deps until done. NodeEntry: %s", this);
    Preconditions.checkNotNull(directDeps, "deps can't be null: %s", this);
    return GroupedList.castAsCompressed(directDeps);
  }