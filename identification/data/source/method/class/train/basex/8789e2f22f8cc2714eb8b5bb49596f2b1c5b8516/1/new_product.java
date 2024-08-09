@SuppressWarnings("unused")
  public Expr optimizeEbv(final QueryContext qc, final VarScope scp) throws QueryException {
    // return true if a deterministic expression returns at least one node
    final SeqType st = seqType();
    if(st.type instanceof NodeType && st.occ.min >= 1 && !has(Flag.UPD) && !has(Flag.NDT)) {
      qc.compInfo(OPTWRITE, this);
      return Bln.TRUE;
    }
    return this;
  }