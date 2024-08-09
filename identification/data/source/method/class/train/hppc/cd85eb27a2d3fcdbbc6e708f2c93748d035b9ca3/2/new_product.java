@Override
  public int removeAll(KTypePredicate<? super KType> predicate) {
    final KType[] keys = this.keys;
    final boolean[] allocated = this.allocated;

    int before = size();
    for (int i = 0; i < allocated.length;) {
      if (allocated[i]) {
        if (predicate.apply(keys[i])) {
          shiftConflictingKeys(i);
          assigned--;
          continue; // Repeat the check for the same index i (shifted).
        }
      }
      i++;
    }

    return before - size();
  }