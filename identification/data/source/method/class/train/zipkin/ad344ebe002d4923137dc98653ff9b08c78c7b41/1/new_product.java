@Override long readLong() {
      return Long.reverseBytes(readLongLe());
    }