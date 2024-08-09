private Item optimize(final QueryContext qc) throws QueryException {
    final Data data = checkData(qc);
    final boolean all = exprs.length > 1 && toBoolean(exprs[1], qc);
    final Options opts = toOptions(2, Q_OPTIONS, new Options(), qc);
    qc.resources.updates().add(new DBOptimize(data, all, opts, qc, info), qc);
    return null;
  }