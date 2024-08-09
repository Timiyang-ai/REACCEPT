@ExperimentalApi
  public <T> void discardAll(Key<T> key) {
    if (isEmpty()) {
      return;
    }
    int writeIdx = 0;
    int readIdx = 0;
    for (; readIdx < size; readIdx++) {
      if (bytesEqual(key.asciiName(), name(readIdx))) {
        continue;
      }
      name(writeIdx, name(readIdx));
      value(writeIdx, value(readIdx));
      writeIdx++;
    }
    int newSize = writeIdx;
    // Multiply by two since namesAndValues is interleaved.
    Arrays.fill(namesAndValues, writeIdx * 2, len(), null);
    size = newSize;
  }