final DBNode open(final QueryContext qc, final boolean id) throws QueryException {
    final Data data = checkData(qc);
    final int v = (int) toLong(exprs[1], qc);
    final int pre = id ? data.pre(v) : v;
    if(pre >= 0 && pre < data.meta.size) return new DBNode(data, pre);
    throw DB_RANGE_X_X_X.get(info, data.meta.name, id ? "ID" : "pre", v);
  }