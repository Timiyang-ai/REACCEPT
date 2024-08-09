void merge(Settings other) {
    for (int i = 0; i < COUNT; i++) {
      if (!other.isSet(i)) continue;
      set(i, other.flags(i), other.get(i));
    }
  }