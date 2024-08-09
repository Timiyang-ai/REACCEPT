void reset() {
    int count = 0;
    size = (sampleSize >>> 1);
    for (int i = 0; i < table.length; i++) {
      count += Long.bitCount(table[i] & ONE_MASK);
      table[i] = (table[i] >>> 1) & RESET_MASK;
    }
    size -= (count >>> 2);
  }