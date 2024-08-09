public void add(final int pre, final FTMatches all) {
    final IntList ps = new IntList();
    for(final FTMatch m : all) {
      for(final FTStringMatch sm : m) {
        for(int s = sm.s; s <= sm.e; s++) ps.add(s);
      }
    }

    int c = find(pre);
    if(c < 0) {
      c = -c - 1;
      if(size == pos.length) pos = Arrays.copyOf(pos, size << 1);
      Array.move(pos, c, 1, size++ - c);
      pos[c] = new FTPos(pre, ps.toArray());
    } else {
      pos[c].union(ps.toArray());
    }
  }