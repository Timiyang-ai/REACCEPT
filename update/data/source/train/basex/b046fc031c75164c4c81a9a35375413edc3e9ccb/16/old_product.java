private ValueBuilder eval(final QueryContext qc, final boolean updating) throws QueryException {
    return eval(qc, checkStr(exprs[0], qc), null, updating);
  }