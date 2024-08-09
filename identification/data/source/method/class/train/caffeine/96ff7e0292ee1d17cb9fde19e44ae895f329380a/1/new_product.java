int indexOf(int item, int i) {
    long hash = (item + SEED[i]) * SEED[i];
    hash += (hash >>> 32);
    return ((int) hash) & tableMask;
  }