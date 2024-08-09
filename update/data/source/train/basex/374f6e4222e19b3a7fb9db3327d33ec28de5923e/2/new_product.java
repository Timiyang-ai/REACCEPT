public boolean instanceOf(final SeqType st) {
    final Type type1 = type, type2 = st.type;
    final Test kind1 = kind, kind2 = st.kind;
    return (type2 == AtomType.ITEM || type1.instanceOf(type2)) && occ.instanceOf(st.occ) &&
      // [LW] complete kind check
      (kind2 == null || kind1 != null && kind1.intersect(kind2) != null);
  }