@NotNull
  public static TokenSet andSet(@NotNull TokenSet a, @NotNull TokenSet b) {
    final TokenSet newSet = new TokenSet((short)Math.min(a.myShift, b.myShift), (short)Math.max(a.myMax, b.myMax));
    for (int i = 0; i < newSet.myWords.length; i++) {
      final int ai = newSet.myShift - a.myShift + i;
      final int bi = newSet.myShift - b.myShift + i;
      newSet.myWords[i] = (0 <= ai && ai < a.myWords.length ? a.myWords[ai] : 0l) & (0 <= bi && bi < b.myWords.length ? b.myWords[bi] : 0l);
    }
    return newSet;
  }