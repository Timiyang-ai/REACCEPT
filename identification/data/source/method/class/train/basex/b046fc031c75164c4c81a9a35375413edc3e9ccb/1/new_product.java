private Item assrt(final QueryContext qc) throws QueryException {
    final Item it = exprs.length < 2 ? null : toItem(exprs[1], qc);
    if(exprs[0].ebv(qc, info).bool(info)) return null;
    throw error(it);
  }