public static TokenSet orSet(TokenSet... sets) {
    TokenSet newSet = new TokenSet();
    for (TokenSet set : sets) {
      for (int i = 0; i < newSet.mySet.length; i++) {
        if (i >= set.mySet.length) break;
        newSet.mySet[i] |= set.mySet[i];
      }
    }
    return newSet;
  }