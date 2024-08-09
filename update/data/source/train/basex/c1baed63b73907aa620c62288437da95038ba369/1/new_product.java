private ValueBuilder eval(final QueryContext ctx, final boolean openDB) throws QueryException {
    return eval(ctx, checkStr(expr[0], ctx), null, openDB);
  }