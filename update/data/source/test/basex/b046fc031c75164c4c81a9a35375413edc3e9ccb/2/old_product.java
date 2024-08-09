private Value head(final QueryContext qc) throws QueryException {
    final Array array = array(0, qc);
    return array.get(checkPos(array, 1));
  }