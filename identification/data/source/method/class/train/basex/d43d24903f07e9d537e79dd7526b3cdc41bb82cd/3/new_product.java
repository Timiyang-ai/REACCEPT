private static void intersect(final SeqType st1, final SeqType st2, final SeqType expected) {
    eq(st1.intersect(st2), expected);
    eq(st2.intersect(st1), expected);
  }