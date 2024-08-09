public Mutability freeze() {
    // No need to track per-Freezable info since everything is immutable now.
    lockedItems = null;
    isFrozen = true;
    return this;
  }