@SuppressWarnings("unused")
  public Expr optimizeEbv(final CompileContext cc) throws QueryException {
    // return true if a deterministic expression returns at least one node
    final SeqType st = seqType();
    return st.type instanceof NodeType && st.oneOrMore() && !has(Flag.UPD) && !has(Flag.NDT) ?
      cc.replaceEbv(this, Bln.TRUE) : this;
  }