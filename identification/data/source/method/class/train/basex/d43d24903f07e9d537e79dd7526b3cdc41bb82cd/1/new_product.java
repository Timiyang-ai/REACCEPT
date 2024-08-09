boolean union(final CmpG g, final QueryContext qc, final VarScope scp) throws QueryException {
    if(op != g.op || !exprs[0].sameAs(g.exprs[0])) return false;
    exprs[1] = new List(info, exprs[1], g.exprs[1]).compile(qc, scp);
    final SeqType st = exprs[1].seqType();
    atomic = atomic && st.zeroOrOne() && !st.mayBeArray();
    return true;
  }