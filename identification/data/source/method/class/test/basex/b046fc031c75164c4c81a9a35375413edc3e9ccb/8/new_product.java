private Value head(final QueryContext qc) throws QueryException {
    final Array array = toArray(exprs[0], qc);
    return array.get(checkPos(array, 1));
  }