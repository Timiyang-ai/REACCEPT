private Array tail(final QueryContext qc) throws QueryException {
    final Array array = array(0, qc);
    return Array.get(array, checkPos(array, 1) + 1, array.arraySize() - 1);
  }