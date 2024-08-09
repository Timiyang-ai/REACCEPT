private Iter search(final QueryContext ctx) throws QueryException {
    final Data data = checkData(ctx);
    final Value terms = ctx.value(expr[1]);
    final FTOptions opts = checkOptions(2, Q_OPTIONS, new FTOptions(), ctx);
    return search(data, terms, opts, this, ctx);
  }