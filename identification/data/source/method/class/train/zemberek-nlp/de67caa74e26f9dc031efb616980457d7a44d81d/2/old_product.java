public T get(int key) {
    if (key < 0) {
      throw new IllegalArgumentException("Key cannot be negative: " + key);
    }
    int slot = firstProbe(key);
    while (true) {
      final int t = keys[slot];
      if (t == EMPTY) {
        return null;
      }
      if (t == DELETED) {
        slot = nextProbe(slot + 1);
        continue;
      }
      if (t == key) {
        return values[slot];
      }
      slot = nextProbe(slot + 1);
    }
  }