private static void union(final SeqType a, final SeqType b, final SeqType res) {
    assertTrue(a + ", " + b, a.union(b).eq(res));
    assertTrue(b + ", " + a, b.union(a).eq(res));
  }