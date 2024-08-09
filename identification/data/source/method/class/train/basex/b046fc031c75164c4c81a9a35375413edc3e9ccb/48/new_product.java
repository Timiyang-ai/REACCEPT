private ValueBuilder eval(final QueryContext qc, final boolean updating) throws QueryException {
    return eval(qc, toToken(exprs[0], qc), null, updating);
  }