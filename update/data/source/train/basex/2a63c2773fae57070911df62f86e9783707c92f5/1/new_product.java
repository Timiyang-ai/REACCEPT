public long[] toArray() {
    // find the last index of a word which is different from 0:
    int i = words.length;
    while(--i >= 0 && words[i] == 0);

    final long[] result = new long[++i];
    System.arraycopy(words, 0, result, 0, i);
    return result;
  }