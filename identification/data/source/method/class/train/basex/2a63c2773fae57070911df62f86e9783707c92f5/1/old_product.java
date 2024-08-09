public long[] getTrimmedWords() {
    // find the last index of a word which is different from 0:
    int i;
    for(i = words.length - 1; i >= 0; i--) if(words[i] != 0) break;

    final long[] result = new long[++i];
    System.arraycopy(words, 0, result, 0, i);

    return result;
  }