@Override
  public Value value(final QueryContext qc) throws QueryException {
    return invoke(toToken(exprs[0], qc), false, qc).value();
  }