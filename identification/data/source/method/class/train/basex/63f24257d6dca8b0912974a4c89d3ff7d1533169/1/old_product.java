public int nextFree(final int i) {
    // calculate the index of the word in the array: i div 2^6 = i >> 6
    int wi = i >>> WORD_POWER;
    // invert the word and skip the first i bits:
    long word = ~words[wi] & WORD_MASK << i;

    if(word != 0) {
      return (wi << WORD_POWER) + numberOfTrailingZeros(word);
    }

    final int wl = words.length;
    while(++wi < wl) {
      if((word = ~words[wi]) != 0) {
        return (wi << WORD_POWER) + numberOfTrailingZeros(word);
      }
    }

    // wi * 2^6:
    return wi << WORD_POWER;
  }