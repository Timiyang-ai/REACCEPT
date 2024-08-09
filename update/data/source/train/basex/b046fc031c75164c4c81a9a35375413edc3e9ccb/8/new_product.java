private Array insertBefore(final QueryContext qc) throws QueryException {
    final Array array = toArray(exprs[0], qc);
    final int p = checkPos(array, toLong(exprs[1], qc), true);
    final Value ins = qc.value(exprs[2]);
    final int as = array.arraySize();
    final ValueList vl = new ValueList(as + 1);
    for(int a = 0; a < as; a++) {
      if(a == p) vl.add(ins);
      vl.add(array.get(a));
    }
    if(p == as) vl.add(ins);
    return vl.array();
  }