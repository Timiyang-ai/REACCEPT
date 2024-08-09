private Map entry(final QueryContext qc, final InputInfo ii) throws QueryException {
    return Map.EMPTY.insert(toAtomItem(exprs[0], qc), qc.value(exprs[1]), ii);
  }