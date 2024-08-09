private static void intersect(final SeqType a, final SeqType b, final SeqType r) {
    assertTrue(a + ", " + b, r != null ? a.intersect(b).eq(r) : a.intersect(b) == null);
    assertTrue(b + ", " + a, r != null ? b.intersect(a).eq(r) : b.intersect(a) == null);
  }