int indexOf(int item, int i) {
    long hash = SEED[i & 3] * item;
    hash += hash >> 32;
    return (int) (hash & LENGTH_MASK);
  }