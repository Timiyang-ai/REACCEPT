public int get(T key) {
    if (key == null) {
      throw new IllegalArgumentException("Key cannot be null.");
    }
    int slot = hash(key) & modulo;
    while (true) {
      final T t = keys[slot];
      if (t == null) {
        return 0;
      }
      if (t == TOMB_STONE) {
        slot = (slot + 1) & modulo;
        continue;
      }
      if (t.equals(key)) {
        return values[slot];
      }
      slot = (slot + 1) & modulo;
    }
  }