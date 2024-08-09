void reset() {
    int count = 0;
    size = (sampleSize >>> 1);
    for (int i = 0; i < table.length; i++) {
      count += Long.bitCount(table[i] & RESET_MASK);
      long a = ((table[i] & MASK_A) >>> 1) & MASK_A;
      long b = ((table[i] & MASK_B) >>> 1) & MASK_B;
      table[i] = a | b;
    }
    size -= (count >>> 2);
  }