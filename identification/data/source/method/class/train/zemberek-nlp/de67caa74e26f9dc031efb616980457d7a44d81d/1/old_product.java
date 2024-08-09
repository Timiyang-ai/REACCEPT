public T get(int key) {
    if (key < 0) {
      throw new IllegalArgumentException("Key cannot be negative: " + key);
    }
    int slot = hash(key);
    while (true) {
      final int t = keys[slot];
      if (t == EMPTY) {
        return null;
      }
      if (t == DELETED) {
        slot = (slot + 1) & modulo;
        continue;
      }
      if (t == key) {
        return values[slot];
      }
      slot = (slot + 1) & modulo;
    }
  }