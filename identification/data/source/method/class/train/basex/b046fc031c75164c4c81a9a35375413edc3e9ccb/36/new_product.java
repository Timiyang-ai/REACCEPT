private Array remove(final QueryContext qc) throws QueryException {
    final Array array = toArray(exprs[0], qc);
    final int p = checkPos(array, toLong(exprs[1], qc));
    final int as = array.arraySize();
    if(p == 0) return Array.get(array, 1, as - 1);
    if(p + 1 == as) return Array.get(array, 0, as - 1);
    final ValueList vl = new ValueList(as - 1);
    for(int a = 0; a < as; a++) if(a != p) vl.add(array.get(a));
    return vl.array();
  }