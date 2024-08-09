boolean union(final CmpG g, final QueryContext qc, final VarScope scp) throws QueryException {
    if(op != g.op || !exprs[0].sameAs(g.exprs[0])) return false;
    exprs[1] = new List(info, exprs[1], g.exprs[1]).compile(qc, scp);
    atomic = atomic && exprs[1].type().zeroOrOne();
    return true;
  }