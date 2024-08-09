private Map entry(final QueryContext qc, final InputInfo ii) throws QueryException {
    return Map.EMPTY.insert(exprs[0].item(qc, ii), qc.value(exprs[1]), ii);
  }