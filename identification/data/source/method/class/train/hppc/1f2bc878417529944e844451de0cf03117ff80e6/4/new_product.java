public boolean remove(KType key) {
    final int mask = this.mask;
    int slot = hashKey(key) & mask;
    while (allocated[slot]) {
      if (Intrinsics.equalsKType(key, keys[slot])) {
        shiftConflictingKeys(slot);
        assigned--;
        return true;
      }
      slot = (slot + 1) & mask;
    }
    return false;
  }