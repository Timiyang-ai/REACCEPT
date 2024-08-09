public boolean instanceOf(final SeqType st) {
    // empty sequence: only check cardinality
    return zero() ? st.mayBeEmpty() :
      (st.type == AtomType.ITEM || type.instanceOf(st.type)) && occ.instanceOf(st.occ) &&
      (st.kind == null || kind != null && kind.intersect(st.kind) != null);
  }