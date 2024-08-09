public int nextFree() {
    final int wl = words.length;
    for(int w = 0; w < wl; w++) {
      final long word = ~words[w];
      if(word != 0) {
        return (w << WORD_POWER) + numberOfTrailingZeros(word);
      }
    }
    return wl << WORD_POWER;
  }