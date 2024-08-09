int indexOf(int item, int i) {
    long hash = SEED[i] * item;
    hash += hash >>> 32;
    return ((int) hash) & tableMask;
  }