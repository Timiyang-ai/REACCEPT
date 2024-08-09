public static TokenSet andSet(TokenSet a, TokenSet b) {
    TokenSet set = new TokenSet();
    final boolean[] aSet = a.mySet;
    final boolean[] bSet = b.mySet;
    final boolean[] newSet = set.mySet;
    final int aLen = aSet.length;
    final int bLen = bSet.length;
    final int andSize = Math.max(newSet.length, Math.max(aLen, bLen));

    for (int i = 0; i < andSize; i++) {
      newSet[i] = (i < aLen && aSet[i]) && (i < bLen && bSet[i]);
    }
    return set;
  }