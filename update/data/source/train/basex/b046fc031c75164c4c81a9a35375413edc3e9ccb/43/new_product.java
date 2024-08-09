private Array subarray(final QueryContext qc) throws QueryException {
    final Array array = toArray(exprs[0], qc);
    final int p = checkPos(array, toLong(exprs[1], qc), true);
    final int l = exprs.length > 2 ? (int) toLong(exprs[2], qc) : array.arraySize() - p;
    if(l < 0) throw ARRAYNEG_X.get(info, l);
    checkPos(array, p + 1 + l, true);
    return Array.get(array, p, l);
  }