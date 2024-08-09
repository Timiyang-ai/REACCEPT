public boolean instanceOf(final SeqType st) {
    final Type t1 = type, t2 = st.type;
    final Test k1 = kind, k2 = st.kind;
    return (t2 == AtomType.ITEM || t1.instanceOf(t2)) && occ.instanceOf(st.occ) &&
      // [LW] complete kind check
      (k2 == null || k1 != null && k1.intersect(k2) != null);
  }