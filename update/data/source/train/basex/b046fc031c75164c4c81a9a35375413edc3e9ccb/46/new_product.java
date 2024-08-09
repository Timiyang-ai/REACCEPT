private Array append(final QueryContext qc) throws QueryException {
    final Array array = toArray(exprs[0], qc);
    final int as = array.arraySize();
    final ValueList vl = new ValueList(as + 1);
    for(final Value v : array.members()) vl.add(v);
    return vl.add(qc.value(exprs[1])).array();
  }