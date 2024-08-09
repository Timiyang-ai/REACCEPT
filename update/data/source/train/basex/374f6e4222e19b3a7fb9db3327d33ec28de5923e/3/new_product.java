public Constr add(final QueryContext qc, final Expr... exprs) throws QueryException {
    final int size = sc.ns.size();
    try {
      for(final Expr expr : exprs) {
        more = false;
        final Iter iter = expr.iter(qc);
        for(Item item; (item = qc.next(iter)) != null && add(qc, item););
      }
      if(!text.isEmpty()) children.add(new FTxt(text.toArray()));
      return this;
    } finally {
      sc.ns.size(size);
    }
  }