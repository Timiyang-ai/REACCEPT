@NotNull
  public static TokenSet orSet(@NotNull TokenSet... sets) {
    if (sets.length == 0) return EMPTY;

    short shift = sets[0].myShift, max = sets[0].myMax;
    for (int i = 1; i < sets.length; i++) {
      if (shift > sets[i].myShift) shift = sets[i].myShift;
      if (max < sets[i].myMax) max = sets[i].myMax;
    }

    final TokenSet newSet = new TokenSet(shift, max);
    for (TokenSet set : sets) {
      final int shiftDiff = set.myShift - newSet.myShift;
      for (int i = 0; i < set.myWords.length; i++) {
        newSet.myWords[i + shiftDiff] |= set.myWords[i];
      }
    }
    return newSet;
  }