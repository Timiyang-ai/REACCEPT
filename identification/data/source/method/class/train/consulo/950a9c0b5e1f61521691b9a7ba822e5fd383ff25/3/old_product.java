public static TokenSet andSet(TokenSet a, TokenSet b) {
    TokenSet set = new TokenSet();
    final boolean[] aset = a.mySet;
    final boolean[] bset = b.mySet;
    final boolean[] newset = set.mySet;
    final int alen = aset.length;
    final int blen = bset.length;
    final int andSize = Math.max(newset.length, Math.max(alen, blen));

    for (int i = 0; i < andSize; i++) {
      newset[i] = (i < alen && aset[i]) && (i < blen && bset[i]);
    }
    return set;
  }