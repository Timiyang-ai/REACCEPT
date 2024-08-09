private Array subarray(final QueryContext qc) throws QueryException {
    final Array array = array(0, qc);
    final int p = checkPos(array, checkItr(exprs[1], qc), true);
    final int l = exprs.length > 2 ? (int) checkItr(exprs[2], qc) : array.arraySize() - p;
    if(l < 0) throw ARRAYNEG.get(info, l);
    checkPos(array, p + 1 + l, true);
    return Array.get(array, p, l);
  }