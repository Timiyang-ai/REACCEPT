private static void union(final SeqType st1, final SeqType st2, final SeqType expected) {
    eq(st1.union(st2), expected);
    eq(st2.union(st1), expected);
  }